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
import com.blacksoft.screen.UIFactory;
import com.blacksoft.state.Config;

import static com.blacksoft.state.Config.MAP_HEIGHT;
import static com.blacksoft.state.Config.MAP_WIDTH;

public class TileCleaner {

    public static void clean(Dungeon dungeon,
                             int x,
                             int y) {
        TiledMapTileLayer tiledMapTileLayer = (TiledMapTileLayer) dungeon.tiledMap.getLayers().get("dungeon");

        GroundTiledMapTile tile = new GroundTiledMapTile(new TextureRegion(new Texture(Gdx.files.internal("tile/Empty.png"))), x, y, Tile.Empty);

        TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
        cell.setTile(tile);
        tiledMapTileLayer.setCell(x, y, cell);
        dungeon.nodes[x][y].tile = Tile.Empty;
        dungeon.nodes[x][y].connectWithNeighbours();
        if (dungeon.nodes[x][y].building != null) {
            dungeon.nodes[x][y].building.destroy();
            dungeon.nodes[x][y].building = null;
        }

        CleanIndicatorsAction.cleanAll(dungeon);
        CleanIndicatorUpdater.update(dungeon);
        Label label = UIFactory.I.createFloatingLabel(-Config.CLEAN_COST_VALUE, x * 16, y * 16);
        label.setColor(Color.valueOf("FFD700"));
    }

    public static boolean cleanConditionally(Dungeon dungeon,
                                             int x,
                                             int y) {
        if (!canClean(dungeon, x, y)) {
            return false;
        }
        clean(dungeon, x, y);
        return true;
    }

    public static boolean isEmptyCorridor(Dungeon dungeon,
                                          int x,
                                          int y) {

        if (x < 0 || y < 0 || x >= MAP_WIDTH || y >= MAP_HEIGHT) {
            return false;
        }

        int adjacent = 0;
        if (isClean(dungeon, x - 1, y)) {
            adjacent += 1;
        }

        if (isClean(dungeon, x, y + 1)) {
            adjacent += 2;
        }

        if (isClean(dungeon, x + 1, y)) {
            adjacent += 4;
        }

        if (isClean(dungeon, x, y - 1)) {
            adjacent += 8;
        }

        return (adjacent == 5 || adjacent == 10) && dungeon.nodes[x][y].tile == Tile.Empty;
    }


    public static boolean isRockWithCorner(Dungeon dungeon,
                                           int x,
                                           int y) {

        if (x < 0 || y < 0 || x >= MAP_WIDTH || y >= MAP_HEIGHT) {
            return false;
        }

        int adjacent = 0;
        if (isClean(dungeon, x - 1, y)) {
            adjacent += 1;
        }

        if (isClean(dungeon, x, y + 1)) {
            adjacent += 2;
        }

        if (isClean(dungeon, x + 1, y)) {
            adjacent += 4;
        }

        if (isClean(dungeon, x, y - 1)) {
            adjacent += 8;
        }

        return (adjacent > 0) && dungeon.nodes[x][y].tile == Tile.Rock;
    }

    public static boolean canClean(Dungeon dungeon,
                                   int x,
                                   int y) {

        // todo cannot clean if neighbour is gate

        int adjacent = 0;
        if (isClean(dungeon, x - 1, y)) {
            adjacent += 1;
        }

        if (isClean(dungeon, x, y + 1)) {
            adjacent += 2;
        }

        if (isClean(dungeon, x + 1, y)) {
            adjacent += 4;
        }

        if (isClean(dungeon, x, y - 1)) {
            adjacent += 8;
        }

        return (adjacent > 0) && !isClean(dungeon, x, y)
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

    public static boolean isClean(Dungeon dungeon,
                                  int x,
                                  int y) {

        if (x < 0 || y < 0 || x >= MAP_WIDTH || y >= MAP_HEIGHT) {
            return false;
        }

        return !dungeon.nodes[x][y].tile.isSolid();
    }

}
