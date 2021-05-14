package com.blacksoft.screen.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class RemoveActorAction extends Action {

    private Group group;

    public RemoveActorAction(Group group,
                             Actor actor) {
        this.group = group;
        this.actor = actor;
    }

    @Override
    public boolean act(float delta) {
        group.removeActor(this.actor);
        return true;
    }
}
