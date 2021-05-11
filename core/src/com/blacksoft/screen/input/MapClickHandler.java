package com.blacksoft.screen.input;

import com.blacksoft.build.UserAction;
import com.blacksoft.dungeon.actions.build.BuildingPlacer;
import com.blacksoft.dungeon.actions.ui.CleanIndicatorUpdater;
import com.blacksoft.dungeon.actions.ui.CleanIndicatorsAction;
import com.blacksoft.dungeon.actions.TileCleaner;
import com.blacksoft.screen.UIFactory;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;
import com.blacksoft.state.UIState;

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

            if (GameState.userAction == UserAction.Clean) {
                if(GameState.gold - CLEAN_COST_VALUE >= 0) {
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

            if (GameState.userAction == UserAction.Place) {
                BuildingPlacer.place(x, y);
            }

        }

        return true;
    }

}
