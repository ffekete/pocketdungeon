package com.blacksoft.skill.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.blacksoft.state.GameState;
import com.blacksoft.user.actions.UserAction;

public class CancellableAction extends Action {

    private Action action;

    public CancellableAction(Action action) {
        this.action = action;
    }


    @Override
    public boolean act(float delta) {

        if(GameState.userAction == UserAction.CancelLast) {
            GameState.userAction = UserAction.Idle;
            return true;
        }

        return this.action.act(delta);
    }

    public static Action of(Action action) {
        return new CancellableAction(action);
    }
}
