package com.blacksoft.dungeon;

import com.blacksoft.dungeon.actions.AbstractAction;
import com.blacksoft.dungeon.building.Building;

public class Node {

    public Tile tile;
    public Building building;

    public float x;
    public float y;

    public boolean canUpgradeBy(AbstractAction action) {
        return building != null && building.canUpgradeBy(action);
    }

}
