package com.blacksoft.screen.input;

import com.blacksoft.dungeon.Tile;
import com.blacksoft.dungeon.actions.TileCleaner;
import com.blacksoft.dungeon.actions.build.BuildingPlacer;
import com.blacksoft.dungeon.actions.ui.CleanIndicatorUpdater;
import com.blacksoft.dungeon.actions.ui.CleanIndicatorsAction;
import com.blacksoft.dungeon.building.Gate;
import com.blacksoft.screen.UIFactory;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;
import com.blacksoft.state.UIState;
import com.blacksoft.user.actions.UserAction;

import static com.blacksoft.state.Config.CLEAN_COST_VALUE;
import static com.blacksoft.state.Config.MAP_HEIGHT;
import static com.blacksoft.state.Config.MAP_WIDTH;
import static com.blacksoft.state.Config.TEXTURE_SIZE;

public class MapClickHandler {

    public static boolean touchDown(int x,
                                    int y) {

        int vx = x / TEXTURE_SIZE;
        int vy = y / TEXTURE_SIZE;

        if (vx >= 0 && vx < MAP_WIDTH && vy >= 0 && vy < MAP_HEIGHT) {
            // LOCK GATE
            if (GameState.dungeon.nodes[vx][vy].tile == Tile.GateClosed) {

                if (((Gate) GameState.dungeon.nodes[vx][vy].building).locked == false) {
                    ((Gate) GameState.dungeon.nodes[vx][vy].building).locked = true;

                    GameState.dungeon.nodes[vx][vy].disconnectFromNeighbours();

                    UIState.closedLockImage.setVisible(true);
                    UIState.openLockImage.setVisible(false);
                    UIState.closedLockImage.setX(vx * TEXTURE_SIZE);
                    UIState.closedLockImage.setY(vy * TEXTURE_SIZE);
                } else {
                    ((Gate) GameState.dungeon.nodes[vx][vy].building).locked = false;
                    GameState.dungeon.nodes[vx][vy].connectWithNeighbours();
                    UIState.openLockImage.setVisible(true);
                    UIState.closedLockImage.setVisible(false);
                    UIState.openLockImage.setX(vx * TEXTURE_SIZE);
                    UIState.openLockImage.setY(vy * TEXTURE_SIZE);
                }
            }
            // CLEAN TILE
            else if (GameState.userAction == UserAction.Clean) {
                if (GameState.gold - CLEAN_COST_VALUE >= 0) {
                    if (TileCleaner.cleanConditionally(GameState.dungeon, vx, vy)) {
                        int old = GameState.gold;
                        GameState.gold -= CLEAN_COST_VALUE;

                        UIFactory.I.updateLabelAmount(old, GameState.gold, UIState.goldLabel, "%s/%s", GameState.maxGoldCapacity);

                        CleanIndicatorsAction.cleanAll(GameState.dungeon);
                        CleanIndicatorUpdater.update(GameState.dungeon);

                        int oldProgress = GameState.loopProgress;
                        GameState.loopProgress += Config.CLEAN_PROGRESS_VALUE;
                        UIFactory.I.updateLabelAmount(oldProgress, GameState.loopProgress, UIState.progressLabel, "%s", null);
                    }
                }
            }
            // PLACE BUILDING
            else if (GameState.userAction == UserAction.Place) {
                if(GameState.selectedAction == null) {
                    return true;
                }
                BuildingPlacer.place(x, y);
            }

        }

        return true;
    }

}
