package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import engine.Game;
import engine.Player;
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
import model.effects.Effect;
import model.world.AntiHero;
import model.world.Champion;
import model.world.Cover;
import model.world.Damageable;
import model.world.Direction;
import model.world.Hero;

public class StartedGame extends JFrame implements ActionListener {
    ActionButtons move;
    ActionButtons endTurn;
    ActionButtons attack;
    ActionButtons castAbility;
    ActionButtons useLeaderAbility;
    Details detailsButton;
    Game game;
    Grid g = new Grid();
    Clip clip;
    ArrayList<moreInfoButton> Dbuttons = new ArrayList<>();
    ArrayList<Damageable> Damgs = new ArrayList<>();
    PriorityQueuePanel p;
    JPanel remainingInfo;
    JPanel secondRemainingInfo;
    public StartedGame(Game game) {
        this.game = game;
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.LIGHT_GRAY);
        buttonsPanel.setPreferredSize(new Dimension(100,80));
        move = new ActionButtons("Move");
        move.setBounds(300 , 50 , 120 , 50);
        attack = new ActionButtons("Attack");
        attack.setBounds(200,50,120,50);
        endTurn = new ActionButtons("EndTurn");
        endTurn.setBounds(200 , 50 , 120 , 50);
        castAbility = new ActionButtons("Cast Ability");
        castAbility.setBounds(100,50,120,50);
        useLeaderAbility = new ActionButtons("Use Leader Ability");
        useLeaderAbility.setBounds(0, 50, 120, 50);
        detailsButton = new Details();
        detailsButton.setBounds(200, 150, 200, 50);
        detailsButton.addActionListener(this);
        buttonsPanel.add(detailsButton);
        buttonsPanel.add(attack);
        buttonsPanel.add(move);
        buttonsPanel.add(endTurn);
        buttonsPanel.add(castAbility);
        buttonsPanel.add(useLeaderAbility);
        useLeaderAbility.addActionListener(this);
        castAbility.addActionListener(this);
        attack.addActionListener(this);
        endTurn.addActionListener(this);
        move.addActionListener(this);
        Champion current = game.getCurrentChampion();


        firstPlayerPanel f = new firstPlayerPanel();
        f.setBackground(Color.white);
        f.setLayout(new BorderLayout());
        JPanel firstPlayerName = new JPanel();
        f.add(firstPlayerName,BorderLayout.NORTH);
        remainingInfo = new JPanel();
        remainingInfo.setBackground(Color.white);
        this.preparePlayerPanels(this.game.getFirstPlayer().getTeam() , remainingInfo);
        f.add(remainingInfo,BorderLayout.CENTER);
        JLabel firstPlayer = new JLabel(game.getFirstPlayer().getName());
        firstPlayerName.add(firstPlayer);
        this.add(f,BorderLayout.WEST);


        secondPlayerPanel s = new secondPlayerPanel();
        s.setBackground(Color.white);
        s.setLayout(new BorderLayout());
        JPanel secondPlayerName = new JPanel();
        s.add(secondPlayerName , BorderLayout.NORTH);
        secondRemainingInfo = new JPanel();
        secondRemainingInfo.setBackground(Color.white);
        this.preparePlayerPanels(this.game.getSecondPlayer().getTeam(), secondRemainingInfo);
        s.add(secondRemainingInfo,BorderLayout.CENTER);
        JLabel secondPlayer = new JLabel(game.getSecondPlayer().getName());
        secondPlayerName.add(secondPlayer);
        this.add(s,BorderLayout.EAST);


        p = new PriorityQueuePanel();
        this.preparePQ();
        this.add(p,BorderLayout.NORTH);
        JButton[][] buttons = g.getBoard();
        Object [][] board = game.getBoard();
        for (int i = 0 ; i<5 ; i++)
            for (int j = 0 ; j<5 ; j++) {
                if (board[i][j] instanceof Champion) {
                    Damgs.add((Damageable) board[i][j]);
                    moreInfoButton details = new moreInfoButton();
                    details.setText("Info");
                    details.addActionListener(this);
                    Dbuttons.add(details);
                    details.setBounds(20 , 75 ,90 ,30);
                    Champion c = (Champion) board[i][j];
                    ImageIcon icon = getPhoto(c);
                    JButton currentButton = buttons[i][j];
                    currentButton.setIcon(icon);
                    currentButton.add(details);
                    currentButton.setLayout(null);
                }
                if (board[i][j] instanceof Cover) {
                    Damgs.add((Damageable) board[i][j]);
                    moreInfoButton details = new moreInfoButton();
                    Dbuttons.add(details);
                    details.addActionListener(this);
                    details.setText("Info");
                    details.setBounds(20 , 75 ,90 ,30);
                    ImageIcon icon = new ImageIcon("Resources/cover.jpg");
                    JButton currentButton = buttons[i][j];
                    currentButton.setIcon(icon);
                    currentButton.add(details);
                    currentButton.setLayout(null);
                }
            }
        ImageIcon logo = new ImageIcon("Resources/marvel.jpg");
        this.setIconImage(logo.getImage());
        this.add(buttonsPanel, BorderLayout.SOUTH);
        this.setTitle("My Marvelous marvel game");
        this.add(g,BorderLayout.CENTER);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        //		this.setUndecorated(true);
        this.setVisible(true);
    }
    public static void main(String[] args) {
    }
    public static ImageIcon getPhoto(Champion c) {
        String name = c.getName();
        switch (name) {
            case "Captain America" : return new ImageIcon("Resources/captainAmerica.png");
            case "Deadpool" : return new ImageIcon("Resources/deadpool.jpg");
            case "Dr Strange" : return new ImageIcon("Resources/drStrange.jpg");
            case "Electro" : return new ImageIcon("Resources/electro.jpg");
            case "Ghost Rider" : return new ImageIcon("Resources/Ghost-Rider.png");
            case "Hela" : return new ImageIcon("Resources/hela.jpg");
            case "Hulk" : return new ImageIcon("Resources/hulk.png");
            case "Iceman" : return new ImageIcon("Resources/iceman.png");
            case "Loki" : return new ImageIcon("Resources/loki.png");
            case "Quicksilver" : return new ImageIcon("Resources/quicksilver.png");
            case "Spiderman" : return new ImageIcon("Resources/spiderman.png");
            case "Thor" : return new ImageIcon("Resources/thor.jpg");
            case "Venom" : return new ImageIcon("Resources/venom.png");
            case "Yellow Jacket" : return new ImageIcon("Resources/yellowjacket.jpg");
            case "Ironman" : return new ImageIcon("Resources/ironman.png");
        }
        return null;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String[] directions = {"UP" , "DOWN" , "RIGHT" , "LEFT"};
        if (e.getSource() == detailsButton) {
            String type = "";
            Champion c = this.game.getCurrentChampion();
            if (c instanceof Hero)
                type = "Hero";
            else if (c instanceof AntiHero)
                type = "AntiHero";
            else
                type = "Villain";
            String AbilityType = "";
            String Abilities = "";
            String Effects = "";
            for (int i = 1 ; i<= c.getAbilities().size() ; i++) {
                Ability a = c.getAbilities().get(i-1);
                if (a instanceof DamagingAbility)
                    AbilityType = "Damaging Ability"
                            + "\nAbility " + i + " Damaging Amount = " + ((DamagingAbility) a).getDamageAmount();
                else if (a instanceof HealingAbility)
                    AbilityType = "Healing Ability"
                            + "\nAbility " + i + " Healing Amount : " + ((HealingAbility) a).getHealAmount();
                else if (a instanceof CrowdControlAbility)
                    AbilityType = "Crowd Control Ability"
                            + "\nAbility " + i + " Effect Name : " + ((CrowdControlAbility) a).getEffect().getName()
                            + "\nAbility " + i + " Effect Duration : " + ((CrowdControlAbility) a).getEffect().getDuration();
                Abilities += "Ability " + i + " Name : " + a.getName()
                        + "\nAbility " + i + " type : " + AbilityType
                        + "\nAbility " + i + " Area of effect : " + a.getCastArea()
                        + "\nAbility " + i + " Cast range : " + a.getCastRange()
                        + "\nAbility " + i + " Mana cost : " + a.getManaCost()
                        + "\nAbility " + i + " Action cost : " + a.getRequiredActionPoints()
                        + "\nAbility " + i + " Base cooldown : " + a.getBaseCooldown()
                        + "\nAbility " + i + " Current cooldown : " + a.getCurrentCooldown() + "\n";
            }
            for (int i = 1 ; i<=c.getAppliedEffects().size() ; i++) {
                Effect eff = c.getAppliedEffects().get(i-1);
                Effects += "Effect " + i + " Name : " + eff.getName()
                        + "\nEffect " + i + " Duration : " + eff.getDuration() + "\n";
            }
            String s = "Name : " + c.getName() + "\nType : " + type
                    + "\nCurrentHP : " + c.getCurrentHP()
                    + "\nCurrent Mana : " + c.getMana()
                    + "\nCurrent Action Points" + c.getCurrentActionPoints()
                    + "\n" + Abilities + Effects
                    + "Attack Damage : " + c.getAttackDamage()
                    + "\nAttack Range : " + c.getAttackRange();
            JTextArea t = new JTextArea(s);
            JScrollPane j = new JScrollPane(t);
            JOptionPane.showMessageDialog(null, j);
        }
        if (e.getSource() == useLeaderAbility) {
            try {
                game.useLeaderAbility();
            }
            catch (LeaderNotCurrentException e1) {
                JOptionPane.showMessageDialog(null, "The current Champion is not the leader" , "Leader not current exception" , JOptionPane.WARNING_MESSAGE);
            }
            catch (LeaderAbilityAlreadyUsedException e2) {
                JOptionPane.showMessageDialog(null, "You have already used your leader ability" , "Leader ability used exception" , JOptionPane.WARNING_MESSAGE);
            }
            catch (CloneNotSupportedException e3) {
                JOptionPane.showMessageDialog(null, "Can not clone this ability effect" , "Clone not supported exception" , JOptionPane.WARNING_MESSAGE);
            }
        }
        if (e.getSource() == castAbility) {
            ArrayList<Ability> a = game.getCurrentChampion().getAbilities();
            Ability[] abilities = a.toArray(new Ability[0]);
            String[] aNames = new String[abilities.length];
            for (int i = 0 ; i<aNames.length ; i++)
                aNames[i] = abilities[i].getName();
            int ability = JOptionPane.showOptionDialog(null, "Please choose the ability you want to cast",
                    "Choosing Ability",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null,
                    aNames, 0);
            if (ability == -1)
                return;
            Ability currentAbility = a.get(ability);
            int x = 0;
            int y = 0;
            try {
                if (currentAbility.getCastArea() == AreaOfEffect.DIRECTIONAL) {
                    int direction = JOptionPane.showOptionDialog(null, "You have choosen a directional ability, please choose the cast direction",
                            "Choosing Ability Direction",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null,
                            directions, 0);
                    if (direction == 0)
                        game.castAbility(currentAbility,Direction.UP);
                    if (direction == 1)
                        game.castAbility(currentAbility,Direction.DOWN);
                    if (direction == 2)
                        game.castAbility(currentAbility,Direction.RIGHT);
                    if (direction == 3)
                        game.castAbility(currentAbility,Direction.LEFT);
                }
                else if (currentAbility.getCastArea() == AreaOfEffect.SINGLETARGET) {
                    String xString = JOptionPane.showInputDialog("You have choosen single target ability, Please choose x co-ordinate");
                    String yString = JOptionPane.showInputDialog("You have choosen single target ability, Please choose y co-ordinate");
                    x = Integer.parseInt(xString);
                    y = Integer.parseInt(yString);
                    game.castAbility(currentAbility,x,y);
                }
                else {
                    game.castAbility(currentAbility);
                }
            }
            catch (NotEnoughResourcesException e1) {
                String s = "";
                if (currentAbility.getManaCost() > game.getCurrentChampion().getMana())
                    s = "You dont have enough mana to cast this Ability";
                else if (currentAbility.getRequiredActionPoints() > game.getCurrentChampion().getCurrentActionPoints())
                    s = "You dont have enough Action Points to cast this ability";
                JOptionPane.showMessageDialog(null, s , "Not enough resources" , JOptionPane.WARNING_MESSAGE);
            }
            catch (AbilityUseException e2) {
                String s = "";
                if (game.getCurrentChampion().searchEffectByName("Silence") >= 0)
                    s = "Current champion is silenced and can not use abilites";
                else if (currentAbility.getCurrentCooldown() > 0)
                    s = "Current ability is still in cooldown and can not be used";
                else if (game.manhattenDistance((Damageable) game.getBoard()[x][y], game.getCurrentChampion()) > currentAbility.getCastRange())
                    s = "Target you are trying to cast ability on is out of range";
                JOptionPane.showMessageDialog(null, s , "Ability use Exception" , JOptionPane.WARNING_MESSAGE);
            }
            catch(CloneNotSupportedException e3) {
                JOptionPane.showMessageDialog(null, "Can not clone this ability effect" , "Clone not supported exception" , JOptionPane.WARNING_MESSAGE);
            }
            catch(InvalidTargetException e4) {
                JOptionPane.showMessageDialog(null, "The target you are trying to cast this ability on is invaild" , "Invaild target exception" , JOptionPane.WARNING_MESSAGE);
            }
        }
        if (e.getSource() == endTurn) {
            this.game.endTurn();
            this.preparePQ();
        }
        moreInfoButton currentButton = null;
        int indexOfButton = -1;
        for (int i = 0 ; i<Dbuttons.size() ; i++) {
            if (e.getSource() == Dbuttons.get(i)) {
                currentButton = Dbuttons.get(i);
                indexOfButton = i;
                break;
            }
        }
        if (currentButton != null) {
            if (Damgs.get(indexOfButton) instanceof Champion) {
                Champion c = (Champion) Damgs.get(indexOfButton);
                boolean isLeader = c.equals(game.getFirstPlayer().getLeader()) || c.equals(game.getSecondPlayer().getLeader());
                String type = "";
                if (c instanceof Hero)
                    type += "Hero";
                else if (c instanceof AntiHero)
                    type += "AntiHero";
                else
                    type += "Villain";
                String AppliedEffects = "";
                for (int i = 0 ; i<c.getAppliedEffects().size() ; i++) {
                    String effect = "Name of applied effect number " + (i+1) + " : " + c.getAppliedEffects().get(i).getName()
                            + ", Duration : " + c.getAppliedEffects().get(i).getDuration();
                    AppliedEffects += effect + "\n";
                }
                String s = "Type : " + type + "\nName : " + c.getName()
                        + "\nCurrentHP : " + c.getCurrentHP() + "\nMax Mana : " +
                        c.getMana() + "\nMax Action Points : " + c.getMaxActionPointsPerTurn()
                        + "\nSpeed : " + c.getSpeed() + "\nAttack Range : " + c.getAttackRange()
                        + "\nAttack Damage : " + c.getAttackDamage() + "\nIs Leader : " + isLeader
                        + "\n" + AppliedEffects;
                JOptionPane.showMessageDialog(null, s);
            }
            else if (Damgs.get(indexOfButton) instanceof Cover) {
                Cover c = (Cover) Damgs.get(indexOfButton);
                String s = "CurrentHP : " + c.getCurrentHP();
                JOptionPane.showMessageDialog(null, s);
            }
        }
        if (e.getSource() == attack) {
            int direction = JOptionPane.showOptionDialog(null, "In which direction would you like to move",
                    "Direction",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null,
                    directions, 0);
            try {
                switch (direction) {
                    case 0 : game.attack(Direction.UP);break;
                    case 1 : game.attack(Direction.DOWN);break;
                    case 2 : game.attack(Direction.RIGHT);break;
                    case 3 : game.attack(Direction.LEFT);break;
                }
            }
            catch (NotEnoughResourcesException e1) {
                JOptionPane.showMessageDialog(null, "You dont have enough action points to attack" , "Not enough resources" , JOptionPane.WARNING_MESSAGE);
            }
            catch (ChampionDisarmedException e2) {
                JOptionPane.showMessageDialog(null, "This champion is disarmed and cant do attacks" , "Champion Disarmed Exception" , JOptionPane.WARNING_MESSAGE);
            }

        }
        if (e.getSource() == move) {
            int direction = JOptionPane.showOptionDialog(null, "In which direction would you like to move",
                    "Direction",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null,
                    directions, 0);
            int indexOfChampion = -1;
            for (int i = 0 ; i<Damgs.size() ; i++) {
                if (Damgs.get(i).equals(game.getCurrentChampion())) {
                    indexOfChampion = i;
                    break;
                }
            }
            try {
                int x = game.getCurrentChampion().getLocation().x;
                int y = game.getCurrentChampion().getLocation().y;
                JButton [][] buttons = g.board;
                switch (direction) {
                    case 0 : game.move(Direction.UP);
                        Icon icon = buttons[x][y].getIcon();
                        buttons[x][y].setIcon(null);
                        buttons[x][y].remove(Dbuttons.get(indexOfChampion));
                        buttons[x+1][y].setIcon(icon);
                        buttons[x+1][y].add(Dbuttons.get(indexOfChampion));
                        buttons[x+1][y].setLayout(null); break;
                    case 1 : game.move(Direction.DOWN);
                        Icon icon1 = buttons[x][y].getIcon();
                        buttons[x][y].setIcon(null);
                        buttons[x][y].remove(Dbuttons.get(indexOfChampion));
                        buttons[x-1][y].setIcon(icon1);
                        buttons[x-1][y].add(Dbuttons.get(indexOfChampion));
                        buttons[x-1][y].setLayout(null); break;
                    case 2 : game.move(Direction.RIGHT);
                        Icon icon2 = buttons[x][y].getIcon();
                        buttons[x][y].setIcon(null);
                        buttons[x][y].remove(Dbuttons.get(indexOfChampion));
                        buttons[x][y+1].setIcon(icon2);
                        buttons[x][y+1].add(Dbuttons.get(indexOfChampion));
                        buttons[x][y+1].setLayout(null); break;
                    case 3 : game.move(Direction.LEFT);
                        Icon icon3 = buttons[x][y].getIcon();
                        buttons[x][y].setIcon(null);
                        buttons[x][y].remove(Dbuttons.get(indexOfChampion));
                        buttons[x][y-1].setIcon(icon3);
                        buttons[x][y-1].add(Dbuttons.get(indexOfChampion));
                        buttons[x][y-1].setLayout(null); break;
                }
            }
            catch (UnallowedMovementException e1) {
                JOptionPane.showMessageDialog(null, "You cant move in this direction" , "Unallowed Movement" , JOptionPane.WARNING_MESSAGE);
            }
            catch (NotEnoughResourcesException e2) {
                JOptionPane.showMessageDialog(null, "You dont have enough action points to move" , "Not enough resources" , JOptionPane.WARNING_MESSAGE);
            }
        }
        this.removeDead();
        this.preparePlayerPanels(this.game.getSecondPlayer().getTeam(), secondRemainingInfo);
        this.preparePlayerPanels(this.game.getFirstPlayer().getTeam() , remainingInfo);
        if (this.game.checkGameOver()!=null) {
            Player winner = this.game.checkGameOver();
            this.dispose();
            WinnerPage end = new WinnerPage(winner);
        }
    }
    public void preparePQ() {
        Component[] components = p.getComponents();
        for (Component component : components) {
            p.remove(component);
        }
        p.revalidate();
        p.repaint();
        JLabel turn = new JLabel("Turn Order");
        turn.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        p.add(turn);
        Comparable[] q = game.getTurnOrder().getElements();
        int size = game.getTurnOrder().size() - 1;
        for (int i = size ; i>=0 ; i--) {
            Champion c = (Champion) q[i];
            ImageIcon photo = getPhoto(c);
            Image newimg = photo.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(newimg);
            p.setLayout(new FlowLayout());
            p.add(new JLabel(icon));
        }
    }
    public void removeDead() {
        for (int i = 0 ; i<Damgs.size() ; i++) {
            if (Damgs.get(i).getCurrentHP() == 0) {
                File file = new File("Resources/siuu.wav");
                try {
                    AudioInputStream as = AudioSystem.getAudioInputStream(file);
                    clip = AudioSystem.getClip();
                    clip.open(as);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                clip.start();
                int x = Damgs.get(i).getLocation().x;
                int y = Damgs.get(i).getLocation().y;
                JButton te = g.board[x][y];
                te.setIcon(null);
                Component[] components = te.getComponents();

                for (Component component : components) {
                    te.remove(component);
                }
                te.revalidate();
                te.repaint();
                Damgs.remove(i);
                Dbuttons.remove(i);
                this.preparePQ();
                i--;
            }
        }
    }
    public void preparePlayerPanels(ArrayList<Champion> team, JPanel remaining) {
        Component[] components = remaining.getComponents();
        for (Component component : components) {
            remaining.remove(component);
        }
        remaining.revalidate();
        remaining.repaint();
        JLabel l = new JLabel("Remaining Champions");
        l.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        JPanel text = new JPanel();
        JPanel champs = new JPanel();
        remaining.setLayout(null);
        text.setBounds(30,30,400,50);
        text.add(l);
        champs.setBounds(0,80,400,300);
        champs.setBackground(Color.white);
        text.setBackground(Color.white);
        remaining.add(text);
        remaining.add(champs);
        for (Champion x : team) {
            ImageIcon photo = getPhoto(x);
            Image newimg = photo.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(newimg);
            champs.add(new JLabel(icon));
        }
        String s = "";
        if (team.equals(game.getFirstPlayer().getTeam())) {
            if (game.isFirstLeaderAbilityUsed())
                s = "Leader Ability : USED";
            else
                s = "Leader Ability : NOT USED";
        }
        else {
            if (game.isSecondLeaderAbilityUsed())
                s = "Leader Ability : USED";
            else
                s = "Leader Ability : NOT USED";
        }
        JLabel le = new JLabel(s);
        le.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        le.setBounds(20,400,300,100);
        remaining.add(le);
    }
}
