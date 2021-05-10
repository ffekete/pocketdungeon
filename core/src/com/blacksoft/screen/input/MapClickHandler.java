package com.blacksoft.screen.input;

import com.blacksoft.build.UserAction;
import com.blacksoft.dungeon.actions.BuildingPlacer;
import com.blacksoft.dungeon.actions.CleanIndicatorUpdater;
import com.blacksoft.dungeon.actions.CleanIndicatorsAction;
import com.blacksoft.dungeon.actions.TileCleaner;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;

import static com.blacksoft.state.Config.MAP_HEIGHT;
import static com.blacksoft.state.Config.MAP_WIDTH;

public class MapClickHandler {

    public static boolean touchDown(int x,
                                    int y) {
        int vx = x / 16;
        int vy = y / 16;

        if (vx >= 0 && vx < MAP_WIDTH && vy >= 0 && vy < MAP_HEIGHT) {

            if (GameState.userAction == UserAction.Clean) {
                if (TileCleaner.cleanConditionally(GameState.dungeon, vx, vy)) {
                    CleanIndicatorsAction.cleanAll(GameState.dungeon);
                    CleanIndicatorUpdater.update(GameState.dungeon);
                    GameState.loopProgress += Config.CLEAN_PROGRESS_VALUE;
                }
            }

            if (GameState.userAction == UserAction.Place) {
                BuildingPlacer.place(x, y);
            }

        }

        return true;
    }

}
