package model.effects;
import java.util.ArrayList;
import model.abilities.Ability;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.world.Champion;

public class PowerUp extends Effect {
    public PowerUp(int duration) {
        super("PowerUp", duration, EffectType.BUFF);
    }
    public void apply(Champion c) {
//		c.getAppliedEffects().add(this);
        ArrayList<Ability> abilities = c.getAbilities();
        for (int i = 0 ; i < abilities.size() ; i ++) {
            if (abilities.get(i) instanceof DamagingAbility) {
                int dmgNew = ((DamagingAbility) abilities.get(i)).getDamageAmount() * 120 / 100;
                ((DamagingAbility) abilities.get(i)).setDamageAmount(dmgNew);
            }
            if (abilities.get(i) instanceof HealingAbility) {
                int healNew = ((HealingAbility) abilities.get(i)).getHealAmount() * 120 / 100;
                ((HealingAbility) abilities.get(i)).setHealAmount(healNew);
            }
        }
    }
    public void remove(Champion c) {
        ArrayList<Ability> abilities = c.getAbilities();
        for (int i = 0 ; i < abilities.size() ; i ++) {
            if (abilities.get(i) instanceof DamagingAbility) {
                int dmgNew = ((DamagingAbility) abilities.get(i)).getDamageAmount() * 100 / 120;
                ((DamagingAbility) abilities.get(i)).setDamageAmount(dmgNew);
            }
            if (abilities.get(i) instanceof HealingAbility) {
                int healNew = ((HealingAbility) abilities.get(i)).getHealAmount() * 100 / 120;
                ((HealingAbility) abilities.get(i)).setHealAmount(healNew);
            }
        }
    }

}
