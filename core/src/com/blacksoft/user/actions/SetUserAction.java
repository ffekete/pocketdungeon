package com.blacksoft.user.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.blacksoft.state.GameState;

public class SetUserAction extends Action {

    private UserAction userAction;

    public SetUserAction(UserAction userAction) {
        this.userAction = userAction;
    }

    @Override
    public boolean act(float delta) {
        GameState.userAction = this.userAction;
        return true;
    }
}
