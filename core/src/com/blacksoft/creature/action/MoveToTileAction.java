package com.blacksoft.creature.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.blacksoft.creature.Creature;
import com.blacksoft.state.GameState;

public class MoveToTileAction extends MoveToAction {

    Vector2 targetNode;
    Vector2 previousNode = null;

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
    }

    @Override
    protected void end() {
        super.end();
    }

    @Override
    public boolean act(float delta) {

        if(GameState.paused) {
            return false;
        }

        Creature creature = (Creature) actor;

        if (this.previousNode == null) {
            creature.previousNode = new Vector2(creature.getX() / 16, creature.getY() / 16);
            this.previousNode = creature.previousNode;
            creature.targetNode = new Vector2(targetNode.x / 16, targetNode.y / 16);
        }

        boolean result = super.act(delta);
        return result;
    }
}
