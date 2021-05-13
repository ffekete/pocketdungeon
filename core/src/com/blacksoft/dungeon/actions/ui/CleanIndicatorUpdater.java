package com.blacksoft.dungeon.actions.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.blacksoft.user.actions.UserAction;
import com.blacksoft.dungeon.Dungeon;
import com.blacksoft.dungeon.GroundTiledMapTile;
import com.blacksoft.dungeon.Tile;
import com.blacksoft.dungeon.actions.TileCleaner;
import com.blacksoft.state.GameState;

import static com.blacksoft.state.Config.MAP_HEIGHT;
import static com.blacksoft.state.Config.MAP_WIDTH;

public class CleanIndicatorUpdater {

    public static void update(Dungeon dungeon) {

        if (GameState.userAction != UserAction.Clean) {
            return;
        }

        TiledMapTileLayer layer = (TiledMapTileLayer) dungeon.tiledMap.getLayers().get(Dungeon.BUILD_INDICATORS_LAYER);

        for (int i = 0; i < MAP_WIDTH; i++) {
            for (int j = 0; j < MAP_HEIGHT; j++) {
                if (GameState.userAction == UserAction.Clean && TileCleaner.canClean(dungeon, i, j)) {
                    if (TileCleaner.canClean(dungeon, i, j)) {
                        layer.setCell(i, j, getEmptyCell(i, j));
                    }
                }
            }
        }
    }

    private static TiledMapTileLayer.Cell getEmptyCell(int x,
                                                       int y) {
        GroundTiledMapTile tile = new GroundTiledMapTile(new TextureRegion(new Texture(Gdx.files.internal("tile/Empty.png"))), x, y, Tile.Empty);
        TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
        cell.setTile(tile);
        return cell;
    }

}
