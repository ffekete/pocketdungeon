package com.blacksoft.creature.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.blacksoft.creature.Creature;
import com.blacksoft.dungeon.actions.TileCleaner;
import com.blacksoft.state.GameState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.blacksoft.creature.util.CreatureActionsUtil.resetCreatureActions;

public class WanderingAction extends Action {

    public WanderingAction(Creature creature) {
        super.setActor(creature);
    }

    @Override
    public boolean act(float delta) {

        if(GameState.paused) {
            return false;
        }

        Creature creature = (Creature) actor;

        int x = (int) actor.getX() / 16;
        int y = (int) actor.getY() / 16;

        List<Vector2> availableTiles = new ArrayList<>();

        if (TileCleaner.isClean(GameState.dungeon, x - 1, y)) {
            availableTiles.add(new Vector2(x - 1, y));
        }

        if (TileCleaner.isClean(GameState.dungeon, x + 1, y)) {
            availableTiles.add(new Vector2(x + 1, y));
        }

        if (TileCleaner.isClean(GameState.dungeon, x, y - 1)) {
            availableTiles.add(new Vector2(x, y - 1));
        }

        if (TileCleaner.isClean(GameState.dungeon, x, y + 1)) {
            availableTiles.add(new Vector2(x, y + 1));
        }

        if (availableTiles.size() > 1) {
            availableTiles.remove(creature.previousNode);
        }

        creature.previousNode = new Vector2(x, y);

        if (!availableTiles.isEmpty()) {
            Vector2 targetNode = availableTiles.get(new Random().nextInt(availableTiles.size()));
            targetNode.x = targetNode.x * 16;
            targetNode.y = targetNode.y * 16;

            MoveToTileAction moveToTileAction = new MoveToTileAction(creature, targetNode);
            moveToTileAction.setActor(creature);
            creature.sequenceActions.addAction(moveToTileAction);
            creature.sequenceActions.addAction(new ResetCreatureActionsAction(creature));

            return true;  // end of this action
        }

        resetCreatureActions(creature);
        return true;
    }
}
