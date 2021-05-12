package com.blacksoft.creature.action;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.blacksoft.creature.Creature;
import com.blacksoft.state.GameState;

public class MoveToTileAction extends MoveToAction {

    Vector2 targetNode;
    Vector2 previousNode = null;
    int cycles = 0;
    long start;

    public MoveToTileAction(Creature creature,
                            Vector2 targetNode) {
        super.actor = creature;
        setPosition(targetNode.x, targetNode.y);
        setDuration(creature.getSpeed());
        System.out.println("duration set to " + creature.getSpeed());
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

        Creature creature = (Creature) actor;

        cycles++;

        if (this.previousNode == null) {
            System.out.println("new cycle");
            creature.previousNode = new Vector2(creature.getX() / 16, creature.getY() / 16);
            this.previousNode = creature.previousNode;
            creature.targetNode = new Vector2(targetNode.x / 16, targetNode.y / 16);
            start = System.currentTimeMillis();
        }

//        System.out.println(creature.previousNode);
//        System.out.println(creature.targetNode + " / " + targetNode);
//        System.out.println(creature.getX() + " " + creature.getY());
//        System.out.println(delta);
//        System.out.println();

        boolean result = super.act(delta);

        if(result) {
            System.out.println("nr of runs: " + cycles);
            System.out.println("Real duration: " + super.getDuration());
            System.out.println("elapsed: " + (System.currentTimeMillis() - start));
            System.out.println("Nr of actions: " + creature.sequenceActions.getActions().size);
            System.out.println("---------------------------------");
        }

        return result;

    }
}
