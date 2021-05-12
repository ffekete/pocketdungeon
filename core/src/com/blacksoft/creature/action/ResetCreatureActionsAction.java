package com.blacksoft.creature.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.blacksoft.creature.Creature;

import static com.blacksoft.creature.action.CreatureDecisionAction.resetCreatureActions;

public class ResetCreatureActionsAction extends Action {

    private Creature creature;

    public ResetCreatureActionsAction(Creature creature) {
        this.creature = creature;
    }

    @Override
    public boolean act(float delta) {
        resetCreatureActions(creature);
        return true;
    }
}
