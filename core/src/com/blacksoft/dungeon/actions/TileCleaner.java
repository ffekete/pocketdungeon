package com.blacksoft.dungeon.actions;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.blacksoft.dungeon.Dungeon;
import com.blacksoft.dungeon.Tile;

import static com.blacksoft.animation.TileAnimationProvider.getAnimatedTiledMapTile;
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
    }

    public static void cleanConditionally(Dungeon dungeon,
                                          int x,
                                          int y) {
        if (!(isClean(dungeon, x - 1, y) || isClean(dungeon, x + 1, y) || isClean(dungeon, x, y - 1) || isClean(dungeon, x, y + 1))) {
            return;
        }
        clean(dungeon, x, y);
    }

    private static boolean isClean(Dungeon dungeon,
                                   int x,
                                   int y) {
        if (x < 0 || y < 0 || x >= MAP_WIDTH || y >= MAP_WIDTH) {
            return false;
        }
        return dungeon.nodes[x][y].tile == Tile.Empty;
    }

}
