package com.blacksoft.creature.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.blacksoft.creature.Creature;

public class IncreaseDefenseAction extends Action {

    private Creature targetCreature;

    public IncreaseDefenseAction(Creature targetCreature) {
        this.targetCreature = targetCreature;
    }

    @Override
    public boolean act(float delta) {


        return true;
    }
}
