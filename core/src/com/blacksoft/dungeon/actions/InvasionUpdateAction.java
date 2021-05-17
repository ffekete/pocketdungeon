package com.blacksoft.dungeon.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.blacksoft.state.GameState;
import com.blacksoft.state.UIState;

public class InvasionUpdateAction extends Action {

    @Override
    public boolean act(float delta) {
        if(GameState.paused) {
            return false;
        }

        if(GameState.loopProgress >= 100) {
            GameState.loopProgress = 0;

            // starts the invasion

            SequenceAction sequenceAction = new SequenceAction();
            sequenceAction.addAction(Actions.delay(1f));
            sequenceAction.addAction(new InvasionStartAction());

            GameState.stage.addAction(sequenceAction);
        }

        UIState.invasionProgressBar.setValue(GameState.loopProgress);


        return false;
    }
}
