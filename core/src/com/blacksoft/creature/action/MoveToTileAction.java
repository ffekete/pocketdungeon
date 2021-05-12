package com.blacksoft.creature.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.blacksoft.creature.Creature;
import com.blacksoft.dungeon.Node;
import com.sun.org.apache.xerces.internal.impl.io.UCSReader;

public class MoveToTileAction extends MoveToAction {

    Vector2 targetNode;

    public MoveToTileAction(Creature creature,
                            Vector2 targetNode) {
        super.actor = creature;
        setPosition(targetNode.x, targetNode.y);
        setDuration(creature.getSpeed());
        this.targetNode = targetNode;
    }

    @Override
    protected void begin() {
        super.begin();
        Creature creature = (Creature) actor;
        creature.previousNode = new Vector2(getX() / 16, getY() / 16);
        creature.targetNode = new Vector2(targetNode.x / 16, targetNode.y / 16);
        setStartPosition(super.actor.getX(), super.actor.getY());
    }

    @Override
    protected void end() {
        super.end();
    }

    @Override
    public boolean act(float delta) {
        boolean finished = super.act(delta);
        if (finished) {
            Creature creature = (Creature) actor;
        }
        return finished;
    }
}
