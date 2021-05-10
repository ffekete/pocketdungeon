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

        if (isClean(dungeon, x - 1, y + 1)) {
            adjacent += 2;
        }

        if (isClean(dungeon, x, y + 1)) {
            adjacent += 4;
        }

        if (isClean(dungeon, x + 1, y + 1)) {
            adjacent += 8;
        }

        if (isClean(dungeon, x + 1, y)) {
            adjacent += 16;
        }

        if (isClean(dungeon, x + 1, y - 1)) {
            adjacent += 32;
        }

        if (isClean(dungeon, x, y - 1)) {
            adjacent += 64;
        }

        if (isClean(dungeon, x - 1, y - 1)) {
            adjacent += 128;
        }


        return (adjacent == 1 ||
                adjacent == 4 ||
                adjacent == 16 ||
                adjacent == 64 ||
                adjacent == 68 ||
                adjacent == 17 ||
                adjacent == 65 ||
                adjacent == 80 ||
                adjacent == 20 ||
                adjacent == 5 ||

                adjacent == 128 + 64 ||
                adjacent == 32 + 64 ||
                adjacent == 16 + 32 ||
                adjacent == 8 + 16 ||
                adjacent == 4 + 8 ||
                adjacent == 2 + 4 ||
                adjacent == 2 + 1 ||
                adjacent == 1 + 128 ||

                adjacent == 1 + 4 + 32 ||
                adjacent == 1 + 64 + 8 ||
                adjacent == 16 + 64 + 2 ||
                adjacent == 128 + 4 + 16 ||
                adjacent == 128 + 64 + 32 ||
                adjacent == 2 + 1 + 128 ||

                adjacent == 2 + 4 + 8 ||
                adjacent == 8 + 16 + 32 ||
                adjacent == 1 + 16 + 32 ||
                adjacent == 1 + 8 + 16 ||
                adjacent == 4 + 64 + 128 ||
                adjacent == 4 + 64 + 32 ||
                adjacent == 4 + 64 + 8 ||
                adjacent == 4 + 64 + 2 ||
                adjacent == 16 + 1 + 128 ||
                adjacent == 16 + 1 + 2 ||


                adjacent == 2 + 4 + 128 + 64 ||
                adjacent == 4 + 8 + 64 + 32 ||
                adjacent == 1 + 128 + 16 + 32 ||
                adjacent == 1 + 2 + 16 + 8 ||

                adjacent == 1 + 2 + 128 + 16 + 32 ||
                adjacent == 1 + 2 + 128 + 16 + 8 ||
                adjacent == 8 + 16 + 32 + 1 + 128 ||
                adjacent == 8 + 16 + 32 + 1 + 2 ||
                adjacent == 2 + 4 + 8 + 128 + 64 ||
                adjacent == 2 + 4 + 8 + 32 + 64 ||
                adjacent == 2 + 4 + 32 + 128 + 64 ||
                adjacent == 8 + 4 + 32 + 128 + 64
        )
                && !isClean(dungeon, x, y);
    }

    public static boolean isClean(Dungeon dungeon,
                                  int x,
                                  int y) {

        if (x < 0 || y < 0 || x >= MAP_WIDTH || y >= MAP_HEIGHT) {
            return false;
        }

        return dungeon.nodes[x][y].tile != Tile.Rock;
    }

}
