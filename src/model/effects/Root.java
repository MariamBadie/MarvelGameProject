package model.effects;
import model.world.Champion;
import model.world.Condition;

public class Root extends Effect {
    public Root( int duration) {
        super("Root", duration, EffectType.DEBUFF);
    }
    public void apply(Champion c) {
        //	c.getAppliedEffects().add(this);
        if (!(c.getCondition().equals(Condition.INACTIVE)))
            c.setCondition(Condition.ROOTED);
    }
    public void remove(Champion c) {
        if (c.getCondition() != Condition.INACTIVE)
            if(c.searchEffectByName("Root") == -1)
                c.setCondition(Condition.ACTIVE);
        //System.out.println(c.getCondition());
//		System.out.println(c.getAppliedEffects().size());
    }
}
