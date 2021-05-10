package com.blacksoft.creature.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.blacksoft.creature.Creature;

public class MoveToTileAction extends MoveToAction {

    public MoveToTileAction(float x, float y) {
        setPosition(x, y);
        setDuration(1f);
    }

    @Override
    public boolean act(float delta) {
        boolean result = super.act(delta);
        if (result == true) {
            Creature creature = (Creature) actor;
            creature.finishedMoving = true;
        }
        return result;
    }
}
