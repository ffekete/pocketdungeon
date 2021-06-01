package com.blacksoft.creature.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.blacksoft.creature.Creature;
import com.blacksoft.creature.Direction;
import com.blacksoft.creature.State;
import com.blacksoft.dungeon.actions.TileTypeDetector;
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
        ((Creature) actor).setState(State.Walk);
    }

    @Override
    protected void end() {
        super.end();
        ((Creature) actor).setState(State.Idle);
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

            if(previousNode.x < creature.targetNode.x) {
                creature.direction = Direction.Right;
            } else {
                creature.direction = Direction.Left;
            }
        }

        if(!TileTypeDetector.canTraverse(GameState.dungeon, (int)creature.targetNode.x,(int)creature.targetNode.y)) {
            creature.setPosition(previousNode.x * 16, previousNode.y * 16);
            creature.addAction(new ResetCreatureActionsAction(creature));
            return true;
        }

        boolean result = super.act(delta);
        return result;
    }
}
