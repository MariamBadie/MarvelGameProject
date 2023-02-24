package model.effects;

import model.world.Champion;

public class Silence extends Effect {
    public Silence( int duration) {
        super("Silence", duration, EffectType.DEBUFF);
    }
    public void apply(Champion c) {
        int maxActionNew = c.getMaxActionPointsPerTurn() + 2;
        c.setMaxActionPointsPerTurn(maxActionNew);
        int actionNew = c.getCurrentActionPoints() + 2;
        c.setCurrentActionPoints(actionNew);
    }
    public void remove(Champion c) {
//		removeEffect(c);
        int maxActionNew = c.getMaxActionPointsPerTurn() - 2;
        c.setMaxActionPointsPerTurn(maxActionNew);
        int actionNew = c.getCurrentActionPoints() - 2;
        c.setCurrentActionPoints(actionNew);
    }
}
