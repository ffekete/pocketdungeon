package com.blacksoft.dungeon.actions;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.blacksoft.dungeon.Dungeon;
import com.blacksoft.dungeon.Tile;

import static com.blacksoft.animation.TileAnimationProvider.getAnimatedTiledMapTile;
import static com.blacksoft.state.Config.MAP_HEIGHT;
import static com.blacksoft.state.Config.MAP_WIDTH;

public class TileCleaner {

    public static void clean(Dungeon dungeon,
                             int x,
                             int y) {
        TiledMapTileLayer tiledMapTileLayer = (TiledMapTileLayer) dungeon.tiledMap.getLayers().get("dungeon");
        AnimatedTiledMapTile animatedTiledMapTile = getAnimatedTiledMapTile(Tile.Empty);
        TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
        cell.setTile(animatedTiledMapTile);
        tiledMapTileLayer.setCell(x, y, cell);
        dungeon.nodes[x][y].tile = Tile.Empty;
        dungeon.nodes[x][y].building = null;

        CleanIndicatorsAction.cleanAll(dungeon);
        CleanIndicatorUpdater.update(dungeon);
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

    public static boolean canClean(Dungeon dungeon,
                                   int x,
                                   int y) {

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

        return (adjacent == 1 ||
                adjacent == 2 ||
                adjacent == 4 ||
                adjacent == 8 ||
                adjacent == 10 ||
                adjacent == 5)

                && dungeon.nodes[x][y].tile == Tile.Rock;
    }

    public static boolean isClean(Dungeon dungeon,
                                  int x,
                                  int y) {

        if (x < 0 || y < 0 || x >= MAP_WIDTH || y >= MAP_HEIGHT) {
            return false;
        }

        return dungeon.nodes[x][y].tile == Tile.Empty;
    }

}
