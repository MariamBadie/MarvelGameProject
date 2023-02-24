package model.world;

import java.awt.Point;
import java.util.ArrayList;

import engine.PriorityQueue;
import model.abilities.Ability;
import model.effects.Effect;
import model.effects.EffectType;

public abstract class Champion implements Damageable , Comparable {
    private String name;
    private int maxHP;
    private int currentHP;
    private int mana;
    private int maxActionPointsPerTurn;
    private int currentActionPoints;
    private int attackRange;
    private int attackDamage;
    private int speed;
    private ArrayList<Ability> abilities;
    private ArrayList<Effect> appliedEffects;
    private Condition condition;
    private Point location;


    public Champion(String name, int maxHP, int mana, int actions, int speed, int attackRange, int attackDamage) {
        this.name = name;
        this.maxHP = maxHP;
        this.mana = mana;
        this.currentHP = this.maxHP;
        this.maxActionPointsPerTurn = actions;
        this.speed = speed;
        this.attackRange = attackRange;
        this.attackDamage = attackDamage;
        this.condition = Condition.ACTIVE;
        this.abilities = new ArrayList<Ability>();
        this.appliedEffects = new ArrayList<Effect>();
        this.currentActionPoints=maxActionPointsPerTurn;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public String getName() {
        return name;
    }

    public void setCurrentHP(int hp) {

        if (hp < 0) {
            currentHP = 0;

        }
        else if (hp > maxHP)
            currentHP = maxHP;
        else
            currentHP = hp;

    }


    public int getCurrentHP() {

        return currentHP;
    }

    public ArrayList<Effect> getAppliedEffects() {
        return appliedEffects;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int currentSpeed) {
        if (currentSpeed < 0)
            this.speed = 0;
        else
            this.speed = currentSpeed;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point currentLocation) {
        this.location = currentLocation;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public ArrayList<Ability> getAbilities() {
        return abilities;
    }

    public int getCurrentActionPoints() {
        return currentActionPoints;
    }

    public void setCurrentActionPoints(int currentActionPoints) {
        if(currentActionPoints>maxActionPointsPerTurn)
            currentActionPoints=maxActionPointsPerTurn;
        else
        if(currentActionPoints<0)
            currentActionPoints=0;
        this.currentActionPoints = currentActionPoints;
    }

    public int getMaxActionPointsPerTurn() {
        return maxActionPointsPerTurn;
    }

    public void setMaxActionPointsPerTurn(int maxActionPointsPerTurn) {
        this.maxActionPointsPerTurn = maxActionPointsPerTurn;
    }

    public int searchEffectByName(String s) {
        for (int i = 0 ; i < this.getAppliedEffects().size() ; i++) {
            if(this.getAppliedEffects().get(i).getName().equals(s))
                return i;
        }
        return -1;
    }
    public int compareTo(Object o) {
        Champion temp = (Champion) o;
        if (this.getSpeed() == temp.getSpeed())
            return this.getName().compareTo(temp.getName());
        return (temp.getSpeed() - this.getSpeed());
    }
    public abstract void useLeaderAbility(ArrayList<Champion> targets) throws CloneNotSupportedException;
    public int getNegativeEffects() {
        for (int i = 0 ; i<this.getAppliedEffects().size() ; i++)
            if (this.getAppliedEffects().get(i).getType() == EffectType.DEBUFF)
                return i;
        return -1;
    }
    public void turnEnd(){
        int i = 0;
        while(i<this.getAppliedEffects().size()) {
            int x = this.getAppliedEffects().get(i).getDuration() - 1;
            Effect e = this.getAppliedEffects().get(i);
            this.getAppliedEffects().get(i).setDuration(x);
            if (x == 0) {
                this.getAppliedEffects().remove(e);
                e.remove(this);
            }
            else
                i++;
        }
        for (int j = 0 ; j<this.getAbilities().size() ; j++) {
            if (this.getAbilities().get(j).getCurrentCooldown() > 0) {
                int x = this.getAbilities().get(j).getCurrentCooldown() - 1;
                this.getAbilities().get(j).setCurrentCooldown(x);
            }
        }
    }
    public int searchAbilityByName(String s) {
        for (int i = 0 ; i<this.getAbilities().size() ; i++)
            if(this.getAbilities().get(i).getName().equals(s))
                return i;
        return -1;
    }
    public abstract void attackTarget(Damageable target);
    public boolean isMember(ArrayList<Champion> champions) {
//		if(champions.contains(this))
//			return true;
//		for (int i = 0 ; i<champions.size() ; i++)
//			if (champions.get(i).(th)
//				return true;
//		return false; // arraylist.contains
        return champions.contains(this);
    }
}
