package com.blacksoft.screen.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.blacksoft.state.GameState;

public class AddActorAction extends Action {

    private Stage stage;

    public AddActorAction(Actor actor, Stage stage) {
        this.actor = actor;
        this.stage = stage;
    }

    @Override
    public boolean act(float delta) {
        stage.addActor(this.actor);
        return true;
    }
}
