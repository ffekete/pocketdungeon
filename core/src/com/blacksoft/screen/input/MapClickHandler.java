package com.blacksoft.screen.input;

import com.blacksoft.build.BuildTool;
import com.blacksoft.dungeon.actions.CleanIndicatorUpdater;
import com.blacksoft.dungeon.actions.TileCleaner;
import com.blacksoft.state.GameState;

import static com.blacksoft.state.Config.MAP_HEIGHT;
import static com.blacksoft.state.Config.MAP_WIDTH;

public class MapClickHandler {


    public static boolean touchDown(int x,
                                    int y) {
        int vx = x / 16;
        int vy = y / 16;

        if (vx >= 0 && vx < MAP_WIDTH && vy >= 0 && vy < MAP_HEIGHT) {

            if(GameState.buildTool == BuildTool.Clean) {
                TileCleaner.cleanConditionally(GameState.dungeon, vx, vy);
                CleanIndicatorUpdater.update(GameState.dungeon);
            }

        }

        return true;
    }

}
