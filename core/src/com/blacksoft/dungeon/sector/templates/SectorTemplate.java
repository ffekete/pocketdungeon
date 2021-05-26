package com.blacksoft.dungeon.sector.templates;

import com.blacksoft.dungeon.Node;
import com.blacksoft.dungeon.objects.*;
import com.blacksoft.state.GameState;

import java.util.List;
import java.util.Random;

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
        node.object = null;

        boolean placeWallObjects = new Random().nextBoolean();
        boolean placeDoor = new Random().nextInt(5) == 0;
        boolean placeDecorObjects = new Random().nextInt(5) == 0;

        switch (c) {
            case 'W':
                node.tile = GameState.baseWallTile;
                break;
            case 'E':
                node.object = new DungeonEntrance();
                node.tile = GameState.baseEmptyTile;
                break;
            case 'D':
                if(placeDoor) {
                    node.object = new Door();
                }
                node.tile = GameState.baseEmptyTile;
                break;
            case '@':
                if(placeDecorObjects) {
                    node.object = DecorObjects.I.pickOneRandom();
                }
                node.tile = GameState.baseEmptyTile;
                break;
            case 'T':
                if(placeWallObjects) {
                    node.object = new Torch();
                }
                node.tile = GameState.baseWallTile;
                break;
            case '.':
                node.tile = GameState.baseEmptyTile;
                break;
        }

        return node;
    }

    public abstract List<Integer> getCompatibility();

}
