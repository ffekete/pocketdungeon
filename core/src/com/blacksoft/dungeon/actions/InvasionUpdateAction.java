package com.blacksoft.dungeon.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
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

            GameState.stage.addAction(Actions.delay(1f));
            GameState.stage.addAction(new InvasionStartAction());
        }

        UIState.invasionProgressBar.setValue(GameState.loopProgress);


        return false;
    }
}
