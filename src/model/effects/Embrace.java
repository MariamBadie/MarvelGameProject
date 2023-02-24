package model.effects;

import model.world.Champion;

public class Embrace extends Effect {
    public Embrace(int duration) {
        super("Embrace", duration, EffectType.BUFF);
    }
    public void apply(Champion c) {
//		c.getAppliedEffects().add(this);
        int hpNew = c.getCurrentHP() + (c.getMaxHP()/5);
        c.setCurrentHP(hpNew);
        int manaNew = c.getMana() * 120 / 100;
        c.setMana(manaNew);
        int speedNew = c.getSpeed() * 120 / 100;
        c.setSpeed(speedNew);
        int attackNew = c.getAttackDamage() * 120 / 100;
        c.setAttackDamage(attackNew);
    }
    public void remove(Champion c) {
        int speedNew = c.getSpeed() * 100 / 120;
        c.setSpeed(speedNew);
        int attackNew = c.getAttackDamage() * 100 / 120;
        c.setAttackDamage(attackNew);
    }
}
