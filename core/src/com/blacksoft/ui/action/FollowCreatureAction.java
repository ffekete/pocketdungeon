package com.blacksoft.ui.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.blacksoft.creature.Creature;

import java.util.function.Supplier;

public class FollowCreatureAction extends Action {

    private Creature creature;
    private Supplier<Actor> target;

    public FollowCreatureAction(Creature creature,
                                Supplier<Actor> target) {
        this.creature = creature;
        this.target = target;
    }

    @Override
    public boolean act(float delta) {
        if (target.get() != null) {
            target.get().setPosition(creature.getX(), creature.getY() + 16);
        }
        return false;
    }

    public void setCreature(Creature creature) {
        this.creature = creature;
    }

    public void setTarget(Supplier<Actor> target) {
        this.target = target;
    }
}
