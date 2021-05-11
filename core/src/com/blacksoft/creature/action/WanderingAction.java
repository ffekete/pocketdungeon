package com.blacksoft.creature.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.blacksoft.creature.Creature;
import com.blacksoft.dungeon.actions.TileCleaner;
import com.blacksoft.state.GameState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WanderingAction extends Action {

    @Override
    public boolean act(float delta) {

        Creature creature = (Creature) actor;

        if (!creature.finishedMoving) {
            return false;
        }

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
            creature.targetNode = availableTiles.get(new Random().nextInt(availableTiles.size()));
            creature.addAction(new MoveToTileAction(creature.targetNode.x * 16, creature.targetNode.y * 16, creature));
            creature.finishedMoving = false;
        }

        return false;
    }
}
