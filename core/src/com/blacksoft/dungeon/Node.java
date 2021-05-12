package com.blacksoft.dungeon;

import com.blacksoft.dungeon.actions.AbstractAction;
import com.blacksoft.dungeon.building.Building;
import com.blacksoft.state.GameState;

import static com.blacksoft.state.Config.MAP_HEIGHT;
import static com.blacksoft.state.Config.MAP_WIDTH;

public class Node {

    public Tile tile;
    public Building building;

    public float x;
    public float y;

    public boolean canUpgradeBy(AbstractAction action) {
        return building != null && building.canUpgradeBy(action);
    }

    public void connectWithNeighbours() {
        connectWith(x - 1, y);
        connectWith(x + 1, y);
        connectWith(x, y - 1);
        connectWith(x, y + 1);

    }

    private void connectWith(float x,
                             float y) {

        int vx = (int) x;
        int vy = (int) y;

        int tvx = (int) x;
        int tvy = (int) y;

        if (tvx >= 0 && tvx < MAP_WIDTH && tvy >= 0 && tvy < MAP_HEIGHT) {
            Node targetNode = GameState.dungeon.nodes[tvx][tvy];

            if (!targetNode.tile.isSolid()) {
                GameState.dungeon.streets.add(new Street(this, targetNode));
                GameState.dungeon.streets.add(new Street(targetNode, this));
            }
        }
    }
}
