package com.blacksoft.dungeon.actions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.blacksoft.dungeon.Dungeon;
import com.blacksoft.dungeon.GroundTiledMapTile;
import com.blacksoft.dungeon.Tile;
import com.blacksoft.dungeon.actions.ui.CleanIndicatorUpdater;
import com.blacksoft.dungeon.actions.ui.CleanIndicatorsAction;
import com.blacksoft.dungeon.building.Gate;
import com.blacksoft.screen.UIFactory;
import com.blacksoft.state.Config;
import com.blacksoft.state.UIState;

import static com.blacksoft.state.Config.MAP_HEIGHT;
import static com.blacksoft.state.Config.MAP_WIDTH;
import static com.blacksoft.state.Config.TEXTURE_SIZE;

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

        return dungeon.nodes[x][y].tile != Tile.GateClosed && dungeon.nodes[x][y].tile != Tile.GateOpened;
    }


    public static boolean canTraverse(Dungeon dungeon,
                                      int x,
                                      int y) {

        if (x < 0 || y < 0 || x >= MAP_WIDTH || y >= MAP_HEIGHT) {
            return false;
        }

        return dungeon.nodes[x][y].tile != Tile.Rock && dungeon.nodes[x][y].tile != Tile.Torch && !(
                dungeon.nodes[x][y].tile == Tile.GateClosed && ((Gate) dungeon.nodes[x][y].building).locked);
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
