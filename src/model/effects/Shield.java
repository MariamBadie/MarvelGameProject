package model.effects;

import model.world.Champion;

public class Shield extends Effect {
    public Shield( int duration) {
        super("Shield", duration, EffectType.BUFF);
    }
    public void apply(Champion c) {
//		c.getAppliedEffects().add(this);
        int speedNew = c.getSpeed() * 102 / 100;
        c.setSpeed(speedNew);
    }
    public void remove(Champion c) {
        int speedNew = c.getSpeed() * 100 / 102;
        c.setSpeed(speedNew);
    }

}
