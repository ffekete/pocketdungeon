package com.blacksoft.dungeon;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.utils.Array;
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

    public int index = 0;

    public boolean canUpgradeBy(AbstractAction action) {
        return building != null && building.canUpgradeBy(action);
    }

    public void disconnectFromNeighbours() {
        System.out.println("disconnecting " + x + " " + y);
        disconnectWith(x - 1, y);
        disconnectWith(x + 1, y);
        disconnectWith(x, y - 1);
        disconnectWith(x, y + 1);
    }


    public void connectWithNeighbours() {
        connectWith(x - 1, y);
        connectWith(x + 1, y);
        connectWith(x, y - 1);
        connectWith(x, y + 1);
    }

    private void connectWith(float x,
                             float y) {

        if (GameState.dungeon == null) { // not initialized yet, nothing to conect here
            return;
        }

        int tvx = (int) x;
        int tvy = (int) y;

        if (tvx >= 0 && tvx < MAP_WIDTH && tvy >= 0 && tvy < MAP_HEIGHT) {
            Node targetNode = GameState.dungeon.nodes[tvx][tvy];

            if (!targetNode.tile.isSolid()) {

                if (!GameState.dungeon.streetsMap.containsKey(this)) {
                    GameState.dungeon.streetsMap.put(this, new Array<Connection<Node>>());
                }

                Street street = new Street(this, targetNode);
                GameState.dungeon.streetsMap.get(this).add(street);
                GameState.dungeon.streets.add(street);

//                // back
                if (!GameState.dungeon.streetsMap.containsKey(targetNode)) {
                    GameState.dungeon.streetsMap.put(targetNode, new Array<Connection<Node>>());
                }

                Street street2 = new Street(targetNode, this);
                GameState.dungeon.streetsMap.get(targetNode).add(street2);
                GameState.dungeon.streets.add(street2);

            }
        }
    }

    private void disconnectWith(float x,
                                float y) {

        if (GameState.dungeon == null) { // not initialized yet, nothing to conect here
            return;
        }

        int tvx = (int) x;
        int tvy = (int) y;

        if (tvx >= 0 && tvx < MAP_WIDTH && tvy >= 0 && tvy < MAP_HEIGHT) {
            Node targetNode = GameState.dungeon.nodes[tvx][tvy];

            Connection<Node> currentConnection = null;
            if (GameState.dungeon.streetsMap.get(targetNode) != null) {
                for (Connection<Node> c : GameState.dungeon.streetsMap.get(targetNode)) {
                    if (c.getToNode() == this) {
                        currentConnection = c;
                    }
                }
                GameState.dungeon.streetsMap.remove(this);
                GameState.dungeon.streetsMap.get(targetNode).removeValue(currentConnection, true);
            }
        }
    }
}
