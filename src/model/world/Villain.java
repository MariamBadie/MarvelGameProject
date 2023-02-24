package model.world;

import java.util.ArrayList;
import java.util.Random;

public class Villain extends Champion {

    public Villain(String name, int maxHP, int maxMana, int actions, int speed, int attackRange, int attackDamage) {
        super(name, maxHP, maxMana, actions, speed, attackRange, attackDamage);
    }
    public void useLeaderAbility(ArrayList<Champion> targets) {
        for (int i = 0 ; i<targets.size() ; i++) {
            targets.get(i).setCondition(Condition.KNOCKEDOUT);
            targets.get(i).setCurrentHP(0);
        }
    }
    public void attackTarget(Damageable target) {
        int attackDMG = this.getAttackDamage();
        if (target instanceof AntiHero || target instanceof Hero)
            attackDMG = this.getAttackDamage() * 150 / 100;
        int hpNew = target.getCurrentHP() - attackDMG;
        Random rn = new Random();
        int x = 0;
        if (target instanceof Champion && ((Champion) target).searchEffectByName("Dodge")>= 0)
            x = rn.nextInt(2);
        if (x==0) {
            if ((target instanceof Champion) && ((Champion) target).searchEffectByName("Shield")>= 0) {
                int index = ((Champion) target).searchEffectByName("Shield");
                ((Champion) target).getAppliedEffects().remove(index);
                int speedNew = ((Champion)target).getSpeed() * 100 / 102;
                ((Champion)target).setSpeed(speedNew);
            }
            else target.setCurrentHP(hpNew);
        }
    }
}
