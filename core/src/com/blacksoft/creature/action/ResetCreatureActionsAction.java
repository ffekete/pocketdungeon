package com.blacksoft.creature.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.blacksoft.creature.Creature;
import com.blacksoft.state.GameState;

import static com.blacksoft.creature.util.CreatureActionsUtil.resetCreatureActions;

public class ResetCreatureActionsAction extends Action {

    private Creature creature;

    public ResetCreatureActionsAction(Creature creature) {
        this.creature = creature;
    }

    @Override
    public boolean act(float delta) {

        if(GameState.paused) {
            return false;
        }

        resetCreatureActions(creature);
        return true;
    }
}
