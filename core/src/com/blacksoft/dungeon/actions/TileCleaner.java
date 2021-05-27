package com.blacksoft.dungeon.actions;

import com.blacksoft.dungeon.Dungeon;
import com.blacksoft.dungeon.Tile;
import com.blacksoft.dungeon.objects.floor.Door;

import static com.blacksoft.state.Config.MAP_HEIGHT;
import static com.blacksoft.state.Config.MAP_WIDTH;

public class TileCleaner {

    public static boolean canClean(Dungeon dungeon,
                                   int x,
                                   int y) {

        int adjacent = 0;
        if (!isWall(dungeon, x - 1, y)) {
            adjacent += 1;
        }

        if (!isWall(dungeon, x, y + 1)) {
            adjacent += 2;
        }

        if (!isWall(dungeon, x + 1, y)) {
            adjacent += 4;
        }

        if (!isWall(dungeon, x, y - 1)) {
            adjacent += 8;
        }

        return adjacent > 0 && isWall(dungeon, x, y)
                && isNotGate(dungeon, x - 1, y)
                && isNotGate(dungeon, x + 1, y)
                && isNotGate(dungeon, x, y - 1)
                && isNotGate(dungeon, x, y + 1);
    }

    public static boolean isNotGate(Dungeon dungeon,
                                    int x,
                                    int y) {

        if (x < 0 || y < 0 || x >= MAP_WIDTH || y >= MAP_HEIGHT) {
            return false;
        }

        return dungeon.nodes[x][y].tile != Tile.DoorClosed && dungeon.nodes[x][y].tile != Tile.DoorOpened;
    }


    public static boolean canTraverse(Dungeon dungeon,
                                      int x,
                                      int y) {

        if (x < 0 || y < 0 || x >= MAP_WIDTH || y >= MAP_HEIGHT) {
            return false;
        }

        return !dungeon.nodes[x][y].tile.isSolid() && !(
                dungeon.nodes[x][y].tile == Tile.DoorClosed && ((Door) dungeon.nodes[x][y].object).locked);
    }

    public static boolean isWall(Dungeon dungeon,
                                 int x,
                                 int y) {

        if (x < 0 || y < 0 || x >= MAP_WIDTH || y >= MAP_HEIGHT) {
            return false;
        }

        return dungeon.nodes[x][y].tile == Tile.Rock || dungeon.nodes[x][y].tile == Tile.Torch;
    }
}
