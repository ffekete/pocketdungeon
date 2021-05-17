package com.blacksoft.creature.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.blacksoft.creature.Creature;

public class ReduceHpAction extends Action {

    private Creature targetCreature;
    private int amount;

    public ReduceHpAction(Creature targetCreature,
                          int amount) {
        this.amount = amount;
        this.targetCreature = targetCreature;
    }

    @Override
    public boolean act(float delta) {

        this.targetCreature.reduceHp(amount);
        return true;
    }
}
