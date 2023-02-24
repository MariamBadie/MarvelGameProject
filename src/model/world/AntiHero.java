package model.world;

import java.util.ArrayList;
import java.util.Random;

import model.effects.Stun;

public class AntiHero extends Champion {

    public AntiHero(String name, int maxHP, int maxMana, int actions, int speed, int attackRange, int attackDamage) {
        super(name, maxHP, maxMana, actions, speed, attackRange, attackDamage);
    }
    public void useLeaderAbility(ArrayList<Champion> targets) {
        for (int i = 0 ; i<targets.size() ; i++) {
            Stun s = new Stun(2);
            s.apply(targets.get(i));
            targets.get(i).getAppliedEffects().add(s);
        }
    }
    public void attackTarget(Damageable target) {
        int attackDMG = this.getAttackDamage();
        if (target instanceof Hero || target instanceof Villain)
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
