package com.blacksoft.screen.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class AddActorAction extends Action {

    private Stage targetStage;
    private Actor targetActor;

    public AddActorAction(Actor actor, Stage targetStage) {
        this.targetActor = actor;
        this.targetStage = targetStage;
    }

    @Override
    public boolean act(float delta) {
        targetStage.addActor(this.targetActor);
        return true;
    }
}
