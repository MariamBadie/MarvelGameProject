package model.effects;

import model.world.Champion;

public class Dodge extends Effect {
    public Dodge(int duration) {
        super("Dodge", duration, EffectType.BUFF);
    }
    public void apply(Champion c) {
//		c.getAppliedEffects().add(this);
        int speed = c.getSpeed() * 105 / 100;
        c.setSpeed(speed);
    }
    public void remove(Champion c) {
        int speedNew = c.getSpeed() * 100 / 105;
        c.setSpeed(speedNew);
    }
}
