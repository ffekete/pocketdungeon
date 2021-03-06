package com.blacksoft.dungeon.sector.templates;

import com.blacksoft.dungeon.Node;
import com.blacksoft.dungeon.objects.floor.*;
import com.blacksoft.dungeon.objects.group.Carpets;
import com.blacksoft.dungeon.objects.group.DecorObjects;
import com.blacksoft.dungeon.objects.group.GroundDecorObjects;
import com.blacksoft.dungeon.objects.group.WallObjects;
import com.blacksoft.dungeon.objects.wall.Moss;
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

                if(i == 2 && j == 2) {
                    nodeMap[i][j].object = new EventObject();
                }

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
            case ',':
                if (new Random().nextInt(5) == 0) {
                    node.object = GroundDecorObjects.I.pickOneRandom();
                }
                node.tile = GameState.baseEmptyTile;
                break;
            case '#':
                node.object = Carpets.I.pickOneRandom();
                node.tile = GameState.baseEmptyTile;
                break;
            case 'g':
                node.object = new Grave();
                node.tile = GameState.baseEmptyTile;
                break;
            case 'b':
                node.object = new BookShelf();
                node.tile = GameState.baseEmptyTile;
                break;
            case 't':
                node.object = new Table();
                node.tile = GameState.baseEmptyTile;
                break;
            case '+':
                node.object = new Cross();
                node.tile = GameState.baseEmptyTile;
                break;
            case 'E':
                node.object = new DungeonEntrance();
                node.tile = GameState.baseEmptyTile;
                break;
            case 'M':
                if (placeWallObjects) {
                    node.object = new Moss();
                }
                node.tile = GameState.baseWallTile;
                break;
            case 'D':
                if (placeDoor) {
                    node.object = new Door();
                    node.aboveObject = new DoorAbove();
                }
                node.tile = GameState.baseEmptyTile;
                break;
            case '@':
                if (placeDecorObjects) {
                    node.object = DecorObjects.I.pickOneRandom();
                }
                node.tile = GameState.baseEmptyTile;
                break;
            case 'T':
                if (placeWallObjects) {
                    node.object = WallObjects.I.pickOneRandom();
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
