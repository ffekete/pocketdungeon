package com.blacksoft.creature.action;

import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.blacksoft.creature.Creature;

public class MoveToTileAction extends MoveToAction {

    private Creature creature;

    public MoveToTileAction(float x,
                            float y,
                            Creature creature) {
        this.creature = creature;
        setPosition(x, y);
        setDuration(creature.getSpeed());
    }

    @Override
    public boolean act(float delta) {
        boolean finished = super.act(delta);
        if (finished) {
            Creature creature = (Creature) actor;
            creature.finishedMoving = true;
        }
        return finished;
    }
}
