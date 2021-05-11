package com.blacksoft.dungeon.actions.ui;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.blacksoft.dungeon.Dungeon;

import static com.blacksoft.state.Config.MAP_HEIGHT;
import static com.blacksoft.state.Config.MAP_WIDTH;

public class CleanIndicatorsAction {

    public static void cleanAll(Dungeon dungeon) {

        TiledMapTileLayer layer = (TiledMapTileLayer) dungeon.tiledMap.getLayers().get(Dungeon.BUILD_INDICATORS_LAYER);

        for (int i = 0; i < MAP_WIDTH; i++) {
            for (int j = 0; j < MAP_HEIGHT; j++) {
                layer.setCell(i, j, null);
            }
        }
    }
}
