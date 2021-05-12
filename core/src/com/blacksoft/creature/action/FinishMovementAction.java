package com.blacksoft.creature.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.blacksoft.creature.Creature;

public class FinishMovementAction extends Action {

    private Creature creature;

    public FinishMovementAction(Creature creature) {
        this.creature = creature;
    }

    @Override
    public boolean act(float delta) {
        this.creature.sequenceActions.reset();
        creature.addAction(creature.sequenceActions);
        this.creature.finishedMoving = true;
        return true;
    }
}
