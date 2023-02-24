package model.effects;

import model.world.Champion;

public class Shock extends Effect {
    public Shock(int duration) {
        super("Shock", duration, EffectType.DEBUFF);
    }
    public void apply(Champion c) {
//		c.getAppliedEffects().add(this);
        int speedNew = c.getSpeed() * 90 / 100;
        c.setSpeed(speedNew);
        int dmgNew = c.getAttackDamage() * 90 / 100;
        c.setAttackDamage(dmgNew);
        int actionNew = c.getMaxActionPointsPerTurn() - 1;
        c.setMaxActionPointsPerTurn(actionNew);
        int currentActionNew = c.getCurrentActionPoints() - 1;
        c.setCurrentActionPoints(currentActionNew);
    }
    public void remove(Champion c) {
        int speedNew = c.getSpeed() * 100 / 90;
        c.setSpeed(speedNew);
        int dmgNew = c.getAttackDamage() * 100 / 90;
        c.setAttackDamage(dmgNew);
        int actionNew = c.getMaxActionPointsPerTurn() + 1;
        c.setMaxActionPointsPerTurn(actionNew);
        int currentActionNew = c.getCurrentActionPoints() + 1;
        c.setCurrentActionPoints(currentActionNew);
    }

}
