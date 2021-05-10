package com.blacksoft.screen.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.blacksoft.state.GameState;

public class AddActorAction extends Action {

    public AddActorAction(Actor actor) {
        this.actor = actor;
    }

    @Override
    public boolean act(float delta) {
        GameState.stage.addActor(this.actor);
        return true;
    }
}
