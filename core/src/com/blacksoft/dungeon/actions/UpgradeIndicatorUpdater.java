package com.blacksoft.dungeon.actions;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.blacksoft.build.UserAction;
import com.blacksoft.dungeon.Dungeon;
import com.blacksoft.state.GameState;

import static com.blacksoft.animation.TileAnimationProvider.getAnimatedTiledMapTileForAnimation;
import static com.blacksoft.state.Config.MAP_HEIGHT;
import static com.blacksoft.state.Config.MAP_WIDTH;

public class UpgradeIndicatorUpdater {

    public static void update(Dungeon dungeon) {

        if (GameState.userAction != UserAction.Place) {
            return;
        }

        TiledMapTileLayer layer = (TiledMapTileLayer) dungeon.tiledMap.getLayers().get(Dungeon.BUILD_INDICATORS_LAYER);

        for (int i = 0; i < MAP_WIDTH; i++) {
            for (int j = 0; j < MAP_HEIGHT; j++) {
                if (GameState.userAction == UserAction.Place && dungeon.nodes[i][j].canUpgradeBy(GameState.selectedAction)) {
                    layer.setCell(i, j, getUpgradeIconCell());
                }
            }
        }
    }


    private static TiledMapTileLayer.Cell getUpgradeIconCell() {
        AnimatedTiledMapTile animatedTiledMapTile = getAnimatedTiledMapTileForAnimation("ui/UpgradeIndicator.png");
        TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
        cell.setTile(animatedTiledMapTile);
        return cell;
    }

}
