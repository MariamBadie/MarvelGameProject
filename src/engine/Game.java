package engine;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.InvalidTargetException;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;
import model.abilities.Ability;
import model.abilities.AreaOfEffect;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.effects.Disarm;
import model.effects.Dodge;
import model.effects.Effect;
import model.effects.EffectType;
import model.effects.Embrace;
import model.effects.PowerUp;
import model.effects.Root;
import model.effects.Shield;
import model.effects.Shock;
import model.effects.Silence;
import model.effects.SpeedUp;
import model.effects.Stun;
import model.world.AntiHero;
import model.world.Champion;
import model.world.Condition;
import model.world.Cover;
import model.world.Damageable;
import model.world.Direction;
import model.world.Hero;
import model.world.Villain;

public class Game {
    private static ArrayList<Champion> availableChampions = new ArrayList<Champion>();
    private static ArrayList<Ability> availableAbilities = new ArrayList<Ability>();
    private Player firstPlayer;
    private Player secondPlayer;
    private Object[][] board;
    private PriorityQueue turnOrder;
    private boolean firstLeaderAbilityUsed;
    private boolean secondLeaderAbilityUsed;
    private final static int BOARDWIDTH = 5;
    private final static int BOARDHEIGHT = 5;

    public Game(Player first, Player second) {
        firstPlayer = first;
        secondPlayer = second;
        board = new Object[BOARDWIDTH][BOARDHEIGHT];
        turnOrder = new PriorityQueue(6);
        placeChampions();
        placeCovers();
        prepareChampionTurns();
    }
    public Champion getCurrentChampion() {

        return (Champion) turnOrder.peekMin();
    }
    public Player checkGameOver() {
        if (firstPlayer.getTeam().size() == 0)
            return secondPlayer;
        else if (secondPlayer.getTeam().size() == 0)
            return firstPlayer;
        return null;
    }
    public void move(Direction d) throws UnallowedMovementException, NotEnoughResourcesException {
        Champion current = this.getCurrentChampion();
        if (current.getCurrentActionPoints() == 0)
            throw new NotEnoughResourcesException();
        else if (current.getCondition() == Condition.ROOTED)
            throw new UnallowedMovementException();
        else {
            Point location = current.getLocation();
            int y = location.x;
            int x = location.y;
            int xnew = x;
            int ynew = y;
            if (d == Direction.DOWN)
                ynew = y-1;
            else if (d == Direction.LEFT)
                xnew = x-1;
            else if (d == Direction.RIGHT)
                xnew = x+1;
            else if (d == Direction.UP)
                ynew = y+1;
            if (xnew > getBoardwidth()-1 || ynew > getBoardheight()-1 || xnew < 0 || ynew < 0)
                throw new UnallowedMovementException();
            if (board[ynew][xnew] == null) {
                Point newLoc = new Point(ynew,xnew);
                board[y][x] = null;
                board[ynew][xnew] = current;
                current.setLocation(newLoc);
                int actionNew = current.getCurrentActionPoints()-1;
                current.setCurrentActionPoints(actionNew);
            }
            else
                throw new UnallowedMovementException();
        }
    }
    public boolean notSameTeam(Champion c1 , Damageable c2) {
        if (c2 instanceof Cover) {
            return true;
        }
        Champion c = (Champion) c2;

        if (c1.isMember(firstPlayer.getTeam()) && c.isMember(secondPlayer.getTeam()))
            return true;
        if (c1.isMember(secondPlayer.getTeam()) && c.isMember(firstPlayer.getTeam()))
            return true;
        return false;

    }
    public void attack(Direction d) throws ChampionDisarmedException, NotEnoughResourcesException {
        Champion current = this.getCurrentChampion();
        if (current.getCurrentActionPoints() < 2)
            throw new NotEnoughResourcesException();
        else if (current.searchEffectByName("Disarm") >= 0)
            throw new ChampionDisarmedException();
        else {
            Damageable target = null;
            Point location = current.getLocation();
            int x = location.y;
            int y = location.x;
            for (int i = 0 ; i<current.getAttackRange() ; i++) {
                if (d==Direction.DOWN) {
                    y--;
                    if (y < 0)
                        break;
                    else if(board[y][x] != null) {
                        Damageable t = (Damageable) board[y][x];
                        if(this.notSameTeam(current, t)) {
                            target = (Damageable) board[y][x];
                            break;
                        }
                    }
                }
                if (d==Direction.UP) {
                    y++;
                    if (y > BOARDHEIGHT-1) {
                        break;
                    }
                    else if(board[y][x] != null) {
                        Damageable t = (Damageable) board[y][x];
                        if(this.notSameTeam(current, t)) {
                            target = (Damageable) board[y][x];
                            break;
                        }
                    }
                }
                if (d==Direction.RIGHT) {
                    x++;
                    if (x > BOARDWIDTH)
                        break;
                    else if(board[y][x] != null) {
                        Damageable t = (Damageable) board[y][x];
                        if(this.notSameTeam(current, t)) {
                            target = (Damageable) board[y][x];
                            break;
                        }
                    }
                }
                if (d==Direction.LEFT) {
                    x--;
                    if (x < 0)
                        break;
                    else if(board[y][x] != null) {
                        Damageable t = (Damageable) board[y][x];
                        if(this.notSameTeam(current, t)) {
                            target = (Damageable) board[y][x];
                            break;
                        }
                    }
                }
            }
            int actionNew = current.getCurrentActionPoints() - 2;
            current.setCurrentActionPoints(actionNew);
            if (target != null) {
                current.attackTarget(target);
                if (target.getCurrentHP() == 0)
                    this.dead(target);
            }
        }
    }
    public void castAbility(Ability a) throws NotEnoughResourcesException, AbilityUseException, InvalidTargetException, CloneNotSupportedException {
        Champion current = this.getCurrentChampion();
        if (current.getCurrentActionPoints()<a.getRequiredActionPoints() || current.getMana() < a.getManaCost())
            throw new NotEnoughResourcesException();
        else if (!(current.getAbilities().contains(a)))
            throw new AbilityUseException();
        else if  (a.getCurrentCooldown() != 0)
            throw new AbilityUseException();
        else if (a.getCastArea() == AreaOfEffect.DIRECTIONAL || a.getCastArea() == AreaOfEffect.SINGLETARGET)
            throw new AbilityUseException();
        else if (current.searchEffectByName("Silence") >= 0)
            throw new AbilityUseException();
        else if (a.getCastArea() == AreaOfEffect.SELFTARGET) {
            ArrayList<Damageable> targets = new ArrayList<Damageable>();
            targets.add(current);
            //			System.out.println(((CrowdControlAbility)a).getEffect().getType());
            //			System.out.println(current.isMember(secondPlayer.getTeam()));
            //			System.out.println(current.isMember(firstPlayer.getTeam()));
            a.execute(targets);
            int newActions = current.getCurrentActionPoints() - a.getRequiredActionPoints();
            current.setCurrentActionPoints(newActions);
            int newMana = current.getMana() - a.getManaCost();
            current.setMana(newMana);
            for (int k = 0 ; k<targets.size() ; k++)
                if (targets.get(k).getCurrentHP() == 0)
                    this.dead(targets.get(k));
        }
        else {
            ArrayList<Damageable> targets = new ArrayList<Damageable>();
            Point location = current.getLocation();
            int x = location.y;
            int y = location.x;
            if (a.getCastArea() == AreaOfEffect.SURROUND) {
                if(x+1 < BOARDWIDTH && board[y][x+1]!=null)
                    targets.add((Damageable)board[y][x+1]);
                if(x-1 >= 0 && board[y][x-1]!=null)
                    targets.add((Damageable)board[y][x-1]);
                if(y+1 < BOARDHEIGHT && board[y+1][x]!=null)
                    targets.add((Damageable)board[y+1][x]);
                if(y-1 >= 0 && board[y-1][x]!=null)
                    targets.add((Damageable)board[y-1][x]);
                if(x+1 < BOARDWIDTH && y+1 < BOARDHEIGHT && board[y+1][x+1]!=null)
                    targets.add((Damageable)board[y+1][x+1]);
                if(x+1 < BOARDWIDTH && y-1 >= 0 && board[y-1][x+1]!=null)
                    targets.add((Damageable)board[y-1][x+1]);
                if(x-1 >= 0 && y+1 < BOARDHEIGHT && board[y+1][x-1]!=null)
                    targets.add((Damageable)board[y+1][x-1]);
                if(x-1 >= 0 && y-1 >= 0 && board[y-1][x-1]!=null)
                    targets.add((Damageable)board[y-1][x-1]);
            }
            else {
                for (int i = 0 ; i<firstPlayer.getTeam().size() ; i++) {
                    int dis = this.manhattenDistance(current, firstPlayer.getTeam().get(i));
                    if (dis <= a.getCastRange())
                        targets.add(firstPlayer.getTeam().get(i));
                }
                for (int i = 0 ; i<secondPlayer.getTeam().size() ; i++) {
                    int dis = this.manhattenDistance(current, secondPlayer.getTeam().get(i));
                    if (dis <= a.getCastRange())
                        targets.add(secondPlayer.getTeam().get(i));
                }
            }
            int newActions = current.getCurrentActionPoints() - a.getRequiredActionPoints();
            current.setCurrentActionPoints(newActions);
            int newMana = current.getMana() - a.getManaCost();
            current.setMana(newMana);
            ArrayList<Damageable> validatedTargets = this.validateTargets(targets, a);
            if (validatedTargets.size() != 0) {
                a.execute(validatedTargets);
                for (int k = 0 ; k<validatedTargets.size() ; k++) {
                    if (validatedTargets.get(k).getCurrentHP() == 0)
                        this.dead(validatedTargets.get(k));
                }
            }
        }
    }
    public void castAbility(Ability a , Direction d) throws NotEnoughResourcesException, AbilityUseException, CloneNotSupportedException {
        Champion current = this.getCurrentChampion();
        if (current.getCurrentActionPoints()<a.getRequiredActionPoints() || current.getMana() < a.getManaCost())
            throw new NotEnoughResourcesException();
        else if  (a.getCurrentCooldown() > 0)
            throw new AbilityUseException();
        else if (a.getCastArea() != AreaOfEffect.DIRECTIONAL)
            throw new AbilityUseException();
        else if (!(current.getAbilities().contains(a)))
            throw new AbilityUseException();
        else if (current.searchEffectByName("Silence") >= 0)
            throw new AbilityUseException();
        else {
            ArrayList<Damageable> targets = new ArrayList<Damageable>();
            Point location = current.getLocation();
            int x = location.y;
            int y = location.x;
            for (int i = 0 ; i<a.getCastRange() ; i++) {
                if (d == Direction.DOWN) {
                    if (y-1 < 0)
                        break;
                    if (y-1 >= 0)
                        y--;
                    if (board[y][x] != null)
                        targets.add((Damageable) board[y][x]);
                }
                if (d == Direction.UP) {
                    if (y+1 >= BOARDHEIGHT)
                        break;
                    if (y+1 < BOARDHEIGHT)
                        y++;
                    if (board[y][x] != null)
                        targets.add((Damageable) board[y][x]);
                }
                if (d == Direction.RIGHT) {
                    if (x+1 >= BOARDWIDTH)
                        break;
                    if (x+1 < BOARDWIDTH)
                        x++;
                    if (board[y][x] != null)
                        targets.add((Damageable) board[y][x]);
                }
                if (d == Direction.LEFT) {
                    if (x-1 < 0)
                        break;
                    if (x-1 >= 0)
                        x--;
                    if (board[y][x] != null)
                        targets.add((Damageable) board[y][x]);
                }
            }
            int newActions = current.getCurrentActionPoints() - a.getRequiredActionPoints();
            current.setCurrentActionPoints(newActions);
            int newMana = current.getMana() - a.getManaCost();
            current.setMana(newMana);
            ArrayList<Damageable> r = this.validateTargets(targets, a);
            if(r.size() != 0) {
                a.execute(r);
                for (int k = 0 ; k<r.size() ; k++)
                    if (r.get(k).getCurrentHP() == 0)
                        this.dead(r.get(k));
            }
        }
    }
    public void  castAbility(Ability a, int x, int y) throws NotEnoughResourcesException, AbilityUseException, InvalidTargetException, CloneNotSupportedException {
        Champion current = this.getCurrentChampion();
        if (current.getCurrentActionPoints()<a.getRequiredActionPoints() || current.getMana() < a.getManaCost())
            throw new NotEnoughResourcesException();
        else if  (a.getCurrentCooldown() > 0)
            throw new AbilityUseException();
        else if (a.getCastArea() != AreaOfEffect.SINGLETARGET)
            throw new AbilityUseException();
        else if (!(current.getAbilities().contains(a)))
            throw new AbilityUseException();
        else if (current.searchEffectByName("Silence") >= 0)
            throw new AbilityUseException();
        else if (board[x][y] == null)
            throw new InvalidTargetException();
        else if (board[x][y] instanceof Cover && a instanceof HealingAbility)
            throw new InvalidTargetException();
        else if (board[x][y] instanceof Cover && a instanceof CrowdControlAbility)
            throw new InvalidTargetException();
        else {
            ArrayList<Damageable> r = new ArrayList<>();
            if (board[x][y] != null)
                r.add((Damageable)board[x][y]);
            ArrayList<Damageable> result = this.validateTargets(r, a);
            if (manhattenDistance((Damageable) board[x][y], current) > a.getCastRange())
                throw new AbilityUseException();
            else if (result.isEmpty()) {
                int newActions = current.getCurrentActionPoints() - a.getRequiredActionPoints();
                current.setCurrentActionPoints(newActions);
                int newMana = current.getMana() - a.getManaCost();
                current.setMana(newMana);
                throw new InvalidTargetException();
            }
            else {
                int newActions = current.getCurrentActionPoints() - a.getRequiredActionPoints();
                current.setCurrentActionPoints(newActions);
                int newMana = current.getMana() - a.getManaCost();
                current.setMana(newMana);
                a.execute(result);
                for (int k = 0 ; k<result.size() ; k++)
                    if (result.get(k).getCurrentHP() == 0)
                        this.dead(result.get(k));
            }
        }
    }
    public void useLeaderAbility() throws LeaderNotCurrentException, LeaderAbilityAlreadyUsedException, CloneNotSupportedException {
        if (!(this.getCurrentChampion().equals(firstPlayer.getLeader())))
            if (!(this.getCurrentChampion().equals(secondPlayer.getLeader())))
                throw new LeaderNotCurrentException();
        if(this.getCurrentChampion().equals(firstPlayer.getLeader())&&this.isFirstLeaderAbilityUsed())
            throw new LeaderAbilityAlreadyUsedException();
        if(this.getCurrentChampion().equals(secondPlayer.getLeader())&&this.isSecondLeaderAbilityUsed())
            throw new LeaderAbilityAlreadyUsedException();
        else {
            ArrayList<Champion> targets = new ArrayList<Champion>();
            Champion leader = this.getCurrentChampion();
            if (leader instanceof Hero) {
                if(leader.equals(firstPlayer.getLeader()))
                    for(int i = 0 ; i<firstPlayer.getTeam().size() ; i++)
                        targets.add(firstPlayer.getTeam().get(i));
                if(leader.equals(secondPlayer.getLeader()))
                    for(int i = 0 ; i<secondPlayer.getTeam().size() ; i++)
                        targets.add(secondPlayer.getTeam().get(i));
            }
            else if (leader instanceof AntiHero) {
                for(int i = 0 ; i<firstPlayer.getTeam().size() ; i++) {
                    if(!(firstPlayer.getTeam().get(i).equals(firstPlayer.getLeader())))
                        targets.add(firstPlayer.getTeam().get(i));
                }
                for (int j = 0 ; j<secondPlayer.getTeam().size() ; j++)
                    if(!(secondPlayer.getTeam().get(j).equals(secondPlayer.getLeader())))
                        targets.add(secondPlayer.getTeam().get(j));
            }
            else if (leader instanceof Villain) {
                if(leader.equals(firstPlayer.getLeader()))
                    for(int i = 0 ; i<secondPlayer.getTeam().size() ; i++)
                        if (secondPlayer.getTeam().get(i).getCurrentHP() <= secondPlayer.getTeam().get(i).getMaxHP() * 30 / 100)
                            targets.add(secondPlayer.getTeam().get(i));
                if(leader.equals(secondPlayer.getLeader()))
                    for(int i = 0 ; i<firstPlayer.getTeam().size() ; i++)
                        if (firstPlayer.getTeam().get(i).getCurrentHP() <= firstPlayer.getTeam().get(i).getMaxHP() * 30 / 100)
                            targets.add(firstPlayer.getTeam().get(i));
            }
            leader.useLeaderAbility(targets);
            if (leader.isMember(firstPlayer.getTeam()))
                this.firstLeaderAbilityUsed = true;
            else
                this.secondLeaderAbilityUsed = true;
            for (int r = 0 ; r<targets.size() ; r++)
                if (targets.get(r).getCurrentHP() == 0)
                    this.dead(targets.get(r));

        }
    }
    public void dead(Damageable d) {
        Point location = d.getLocation();
        int x = location.x;
        int y = location.y;
        board[x][y] = null;
        d.setCurrentHP(0);
        if (d instanceof Champion) {
            Champion c = (Champion) d;
            c.setCondition(Condition.KNOCKEDOUT);
            PriorityQueue temp = new PriorityQueue(6);
            while(!turnOrder.isEmpty()) {
                if (((Champion)turnOrder.peekMin()).equals(c))
                    turnOrder.remove();
                else
                    temp.insert(((Champion)turnOrder.remove()));
            }
            while(!temp.isEmpty())
                turnOrder.insert(((Champion)temp.remove()));
            if(c.isMember(firstPlayer.getTeam()))
                for (int i = 0 ; i<firstPlayer.getTeam().size() ; i++)
                    if (firstPlayer.getTeam().get(i).equals(c))
                        firstPlayer.getTeam().remove(i);
            if(c.isMember(secondPlayer.getTeam()))
                for (int i = 0 ; i<secondPlayer.getTeam().size() ; i++)
                    if (secondPlayer.getTeam().get(i).equals(c))
                        secondPlayer.getTeam().remove(i);
        }
    }
    public void endTurn() {
        turnOrder.remove();
        if (this.turnOrder.isEmpty())
            this.prepareChampionTurns();
        Champion c = (Champion)this.turnOrder.peekMin();
        while(!(this.turnOrder.isEmpty()) && c.getCondition() == Condition.INACTIVE) {
            c.turnEnd();
            turnOrder.remove();
            if (!turnOrder.isEmpty())
                c = (Champion)this.turnOrder.peekMin();
        }
        if (turnOrder.isEmpty())
            this.prepareChampionTurns();
        while(!(this.turnOrder.isEmpty()) && c.getCondition() == Condition.INACTIVE) {
            c.turnEnd();
            turnOrder.remove();
            if (!turnOrder.isEmpty())
                c = (Champion)this.turnOrder.peekMin();
        }
        c.turnEnd();
        int newAction = c.getMaxActionPointsPerTurn();
        c.setCurrentActionPoints(newAction);
    }
    private void prepareChampionTurns() {
        for(int i = 0 ; i<firstPlayer.getTeam().size() ; i++) {
            Champion c1 = firstPlayer.getTeam().get(i);
            if (c1.getCondition() != Condition.KNOCKEDOUT)
                turnOrder.insert(c1);
        }
        for (int j = 0 ; j<secondPlayer.getTeam().size() ; j++) {
            Champion c2 = secondPlayer.getTeam().get(j);
            if (c2.getCondition() != Condition.KNOCKEDOUT)
                turnOrder.insert(c2);
        }
    }
    public ArrayList<Damageable> validateTargets(ArrayList<Damageable> targets , Ability a){
        Champion current = this.getCurrentChampion();
        ArrayList<Damageable> result = new ArrayList<Damageable>();
        if (a instanceof CrowdControlAbility) {
            if(current.isMember(firstPlayer.getTeam())&& ((CrowdControlAbility)a).getEffect().getType() == EffectType.BUFF)
                for(int i = 0 ; i<targets.size() ; i++) {
                    if(targets.get(i) instanceof Champion && ((Champion)targets.get(i)).isMember(firstPlayer.getTeam()))
                        result.add(targets.get(i));
                }
            if(current.isMember(firstPlayer.getTeam())&& ((CrowdControlAbility)a).getEffect().getType() == EffectType.DEBUFF)
                for(int i = 0 ; i<targets.size() ; i++) {
                    if(targets.get(i) instanceof Champion && ((Champion)targets.get(i)).isMember(secondPlayer.getTeam()))
                        result.add(targets.get(i));
                }
            if(current.isMember(secondPlayer.getTeam())&& ((CrowdControlAbility)a).getEffect().getType() == EffectType.DEBUFF)
                for(int i = 0 ; i<targets.size() ; i++) {
                    if(targets.get(i) instanceof Champion && ((Champion)targets.get(i)).isMember(firstPlayer.getTeam()))
                        result.add(targets.get(i));
                }
            if(current.isMember(secondPlayer.getTeam())&& ((CrowdControlAbility)a).getEffect().getType() == EffectType.BUFF)
                for(int i = 0 ; i<targets.size() ; i++) {
                    if(targets.get(i) instanceof Champion && ((Champion)targets.get(i)).isMember(secondPlayer.getTeam()))
                        result.add(targets.get(i));
                }
        }
        if (a instanceof HealingAbility) {
            if(current.isMember(firstPlayer.getTeam()))
                for(int i = 0 ; i<targets.size() ; i++) {
                    if(targets.get(i) instanceof Champion && ((Champion)targets.get(i)).isMember(firstPlayer.getTeam()))
                        result.add(targets.get(i));
                }
            else if(current.isMember(secondPlayer.getTeam()))
                for(int i = 0 ; i<targets.size() ; i++) {
                    if(targets.get(i) instanceof Champion && ((Champion)targets.get(i)).isMember(secondPlayer.getTeam()))
                        result.add(targets.get(i));
                }
        }
        if (a instanceof DamagingAbility) {
            if(current.isMember(firstPlayer.getTeam()))
                for(int i = 0 ; i<targets.size() ; i++) {
                    if(targets.get(i) instanceof Champion && ((Champion)targets.get(i)).isMember(secondPlayer.getTeam()))
                        result.add(targets.get(i));
                    if (targets.get(i) instanceof Cover)
                        result.add(targets.get(i));
                }
            else if(current.isMember(secondPlayer.getTeam()))
                for(int i = 0 ; i<targets.size() ; i++) {
                    if(targets.get(i) instanceof Champion && ((Champion)targets.get(i)).isMember(firstPlayer.getTeam()))
                        result.add(targets.get(i));
                    if (targets.get(i) instanceof Cover)
                        result.add(targets.get(i));
                }
        }
        return result;
    }
    public static int manhattenDistance(Damageable target1 , Damageable target2) {
        int sumx = target1.getLocation().x - target2.getLocation().x;
        int sumy = target1.getLocation().y - target2.getLocation().y;
        if (sumx < 0)
            sumx = sumx * -1;
        if (sumy < 0)
            sumy = sumy * -1;
        int sum = sumx + sumy;
        return sum;
    }
    public static void loadAbilities(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line = br.readLine();
        while (line != null) {
            String[] content = line.split(",");
            Ability a = null;
            AreaOfEffect ar = null;
            switch (content[5]) {
                case "SINGLETARGET":
                    ar = AreaOfEffect.SINGLETARGET;
                    break;
                case "TEAMTARGET":
                    ar = AreaOfEffect.TEAMTARGET;
                    break;
                case "SURROUND":
                    ar = AreaOfEffect.SURROUND;
                    break;
                case "DIRECTIONAL":
                    ar = AreaOfEffect.DIRECTIONAL;
                    break;
                case "SELFTARGET":
                    ar = AreaOfEffect.SELFTARGET;
                    break;

            }
            Effect e = null;
            if (content[0].equals("CC")) {
                switch (content[7]) {
                    case "Disarm":
                        e = new Disarm(Integer.parseInt(content[8]));
                        break;
                    case "Dodge":
                        e = new Dodge(Integer.parseInt(content[8]));
                        break;
                    case "Embrace":
                        e = new Embrace(Integer.parseInt(content[8]));
                        break;
                    case "PowerUp":
                        e = new PowerUp(Integer.parseInt(content[8]));
                        break;
                    case "Root":
                        e = new Root(Integer.parseInt(content[8]));
                        break;
                    case "Shield":
                        e = new Shield(Integer.parseInt(content[8]));
                        break;
                    case "Shock":
                        e = new Shock(Integer.parseInt(content[8]));
                        break;
                    case "Silence":
                        e = new Silence(Integer.parseInt(content[8]));
                        break;
                    case "SpeedUp":
                        e = new SpeedUp(Integer.parseInt(content[8]));
                        break;
                    case "Stun":
                        e = new Stun(Integer.parseInt(content[8]));
                        break;
                }
            }
            switch (content[0]) {
                case "CC":
                    a = new CrowdControlAbility(content[1], Integer.parseInt(content[2]), Integer.parseInt(content[4]),
                            Integer.parseInt(content[3]), ar, Integer.parseInt(content[6]), e);
                    break;
                case "DMG":
                    a = new DamagingAbility(content[1], Integer.parseInt(content[2]), Integer.parseInt(content[4]),
                            Integer.parseInt(content[3]), ar, Integer.parseInt(content[6]), Integer.parseInt(content[7]));
                    break;
                case "HEL":
                    a = new HealingAbility(content[1], Integer.parseInt(content[2]), Integer.parseInt(content[4]),
                            Integer.parseInt(content[3]), ar, Integer.parseInt(content[6]), Integer.parseInt(content[7]));
                    break;
            }
            availableAbilities.add(a);
            line = br.readLine();
        }
        br.close();
    }

    public static void loadChampions(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line = br.readLine();
        while (line != null) {
            String[] content = line.split(",");
            Champion c = null;
            switch (content[0]) {
                case "A":
                    c = new AntiHero(content[1], Integer.parseInt(content[2]), Integer.parseInt(content[3]),
                            Integer.parseInt(content[4]), Integer.parseInt(content[5]), Integer.parseInt(content[6]),
                            Integer.parseInt(content[7]));
                    break;

                case "H":
                    c = new Hero(content[1], Integer.parseInt(content[2]), Integer.parseInt(content[3]),
                            Integer.parseInt(content[4]), Integer.parseInt(content[5]), Integer.parseInt(content[6]),
                            Integer.parseInt(content[7]));
                    break;
                case "V":
                    c = new Villain(content[1], Integer.parseInt(content[2]), Integer.parseInt(content[3]),
                            Integer.parseInt(content[4]), Integer.parseInt(content[5]), Integer.parseInt(content[6]),
                            Integer.parseInt(content[7]));
                    break;
            }

            c.getAbilities().add(findAbilityByName(content[8]));
            c.getAbilities().add(findAbilityByName(content[9]));
            c.getAbilities().add(findAbilityByName(content[10]));
            availableChampions.add(c);
            line = br.readLine();
        }
        br.close();
    }

    private static Ability findAbilityByName(String name) {
        for (Ability a : availableAbilities) {
            if (a.getName().equals(name))
                return a;
        }
        return null;
    }

    public void placeCovers() {
        int i = 0;
        while (i < 5) {
            int x = ((int) (Math.random() * (BOARDWIDTH - 2))) + 1;
            int y = (int) (Math.random() * BOARDHEIGHT);

            if (board[x][y] == null) {
                board[x][y] = new Cover(x, y);
                i++;
            }
        }

    }

    public void placeChampions() {
        int i = 1;
        for (Champion c : firstPlayer.getTeam()) {
            board[0][i] = c;
            c.setLocation(new Point(0, i));
            i++;
        }
        i = 1;
        for (Champion c : secondPlayer.getTeam()) {
            board[BOARDHEIGHT - 1][i] = c;
            c.setLocation(new Point(BOARDHEIGHT - 1, i));
            i++;
        }

    }

    public static ArrayList<Champion> getAvailableChampions() {
        return availableChampions;
    }

    public static ArrayList<Ability> getAvailableAbilities() {
        return availableAbilities;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public Object[][] getBoard() {
        return board;
    }

    public PriorityQueue getTurnOrder() {
        return turnOrder;
    }

    public boolean isFirstLeaderAbilityUsed() {
        return firstLeaderAbilityUsed;
    }

    public boolean isSecondLeaderAbilityUsed() {
        return secondLeaderAbilityUsed;
    }

    public static int getBoardwidth() {
        return BOARDWIDTH;
    }

    public static int getBoardheight() {
        return BOARDHEIGHT;
    }

}
