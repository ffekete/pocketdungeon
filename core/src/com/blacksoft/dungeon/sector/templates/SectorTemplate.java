package com.blacksoft.dungeon.sector.templates;

import com.blacksoft.dungeon.Node;
import com.blacksoft.dungeon.Tile;
import com.blacksoft.dungeon.building.DungeonEntrance;

import java.util.List;

import static com.blacksoft.state.Config.SECTOR_SIZE;

public abstract class SectorTemplate {

    public abstract Character[][] getStringTemplate();

    public Node[][] toNodeMap() {

        Node[][] nodeMap = new Node[SECTOR_SIZE][SECTOR_SIZE];

        for (int i = 0; i < SECTOR_SIZE; i++) {
            for (int j = 0; j < SECTOR_SIZE; j++) {

                nodeMap[j][i] = mapToNode(getStringTemplate()[SECTOR_SIZE - i - 1][j]);

            }
        }

        return nodeMap;
    }

    private Node mapToNode(Character c) {

        Node node = new Node();
        node.building = null;

        switch (c) {
            case 'W':
                node.tile = Tile.Rock;
                break;
            case 'E':
                node.building = new DungeonEntrance();
                node.tile = Tile.DungeonEntrance;
                break;
            case '.':
                node.tile = Tile.Empty;
                break;
        }

        return node;
    }

    public abstract List<Integer> getCompatibility();

}
