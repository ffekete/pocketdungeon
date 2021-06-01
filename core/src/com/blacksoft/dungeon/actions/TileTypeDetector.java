package com.blacksoft.dungeon.actions;

import com.blacksoft.dungeon.Dungeon;
import com.blacksoft.dungeon.Tile;

import static com.blacksoft.state.Config.MAP_HEIGHT;
import static com.blacksoft.state.Config.MAP_WIDTH;

public class TileTypeDetector {

    public static boolean canTraverse(Dungeon dungeon,
                                      int x,
                                      int y) {

        if (x < 0 || y < 0 || x >= MAP_WIDTH || y >= MAP_HEIGHT) {
            return false;
        }

        return !dungeon.nodes[x][y].tile.isSolid();
    }

    public static boolean isWall(Dungeon dungeon,
                                 int x,
                                 int y) {

        if (x < 0 || y < 0 || x >= MAP_WIDTH || y >= MAP_HEIGHT) {
            return false;
        }

        return dungeon.nodes[x][y].tile == Tile.BrickWall;
    }
}
