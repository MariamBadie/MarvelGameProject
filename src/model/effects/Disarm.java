package model.effects;
import java.util.ArrayList;

import model.abilities.Ability;
import model.abilities.AreaOfEffect;
import model.abilities.DamagingAbility;
import model.world.Champion;
import model.abilities.*;
public class Disarm extends Effect {
    public Disarm( int duration) {
        super("Disarm", duration, EffectType.DEBUFF);
    }
    public void apply(Champion c) {
//		c.getAppliedEffects().add(this);
        DamagingAbility punch = new DamagingAbility("Punch",
                0,
                1,
                1,
                AreaOfEffect.SINGLETARGET,
                1,
                50);
        c.getAbilities().add(punch);
    }
    public void remove(Champion c) {
        ArrayList<Ability> A = c.getAbilities();
        for(int j = 0 ; j<A.size() ; j++)
            if(A.get(j).getName().equals("Punch")) {
                A.remove(j);
                break;
            }
    }
}
