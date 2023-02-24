package model.effects;

import model.world.Champion;

public class SpeedUp extends Effect{
    public SpeedUp(int duration) {
        super("SpeedUp",duration,EffectType.BUFF);
    }
    public void apply(Champion c) {
        int speedNew = c.getSpeed() * 115 / 100;
        c.setSpeed(speedNew);
        int actionNew = c.getMaxActionPointsPerTurn() + 1;
        c.setMaxActionPointsPerTurn(actionNew);
        int currentActionNew = c.getCurrentActionPoints() + 1;
        c.setCurrentActionPoints(currentActionNew);
    }
    public void remove(Champion c) {
//		removeEffect(c);
        int speedNew = c.getSpeed() * 100 / 115;
        c.setSpeed(speedNew);
        int currentActionNew = c.getCurrentActionPoints() - 1;
        c.setCurrentActionPoints(currentActionNew);
        int actionNew = c.getMaxActionPointsPerTurn() - 1;
        c.setMaxActionPointsPerTurn(actionNew);
    }

}
