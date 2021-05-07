package com.blacksoft.screen.input;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.blacksoft.state.GameState;

import static com.blacksoft.state.Config.MAP_HEIGHT;
import static com.blacksoft.state.Config.MAP_WIDTH;

public class MapMouseMovedHandler {

    public static boolean mouseMoved(InputEvent event,
                                     float x,
                                     float y) {
        int vx = (int) x / 16;
        int vy = (int) y / 16;

        if (vx >= 0 && vx < MAP_WIDTH && vy >= 0 && vy < MAP_HEIGHT) {
            GameState.tileMarker.setVisible(true);

            GameState.tileMarker.setX(((int) x / 16) * 16);
            GameState.tileMarker.setY(((int) y / 16) * 16);
        } else {
            GameState.tileMarker.setVisible(false);
        }

        return true;
    }

}
