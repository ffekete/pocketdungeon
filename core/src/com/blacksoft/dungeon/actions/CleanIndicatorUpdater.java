package com.blacksoft.dungeon.actions;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.blacksoft.build.BuildTool;
import com.blacksoft.dungeon.Dungeon;
import com.blacksoft.dungeon.Tile;
import com.blacksoft.state.GameState;

import static com.blacksoft.animation.TileAnimationProvider.getAnimatedTiledMapTile;
import static com.blacksoft.state.Config.MAP_HEIGHT;
import static com.blacksoft.state.Config.MAP_WIDTH;

public class CleanIndicatorUpdater {

    public static void update(Dungeon dungeon) {

        if (GameState.buildTool != BuildTool.Clean) {
            return;
        }

        TiledMapTileLayer layer = (TiledMapTileLayer) dungeon.tiledMap.getLayers().get(Dungeon.BUILD_INDICATORS_LAYER);

        for (int i = 0; i < MAP_WIDTH; i++) {
            for (int j = 0; j < MAP_HEIGHT; j++) {
                layer.setCell(i, j, null);
                if (TileCleaner.canClean(dungeon, i, j)) {
                    if (TileCleaner.canClean(dungeon, i, j)) {
                        layer.setCell(i, j, getEmptyCell());
                    }
                }
            }
        }
    }

    private static TiledMapTileLayer.Cell getEmptyCell() {
        AnimatedTiledMapTile animatedTiledMapTile = getAnimatedTiledMapTile(Tile.Empty);
        TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
        cell.setTile(animatedTiledMapTile);
        return cell;
    }

}
