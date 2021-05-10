package com.blacksoft.screen.input;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.blacksoft.state.GameState;

import static com.blacksoft.state.Config.MAP_HEIGHT;
import static com.blacksoft.state.Config.MAP_WIDTH;
import static com.blacksoft.state.Config.TEXTURE_SIZE;

public class MapMouseMovedHandler {

    public static boolean mouseMoved(InputEvent event,
                                     float x,
                                     float y) {
        int vx = (int) x / TEXTURE_SIZE;
        int vy = (int) y / TEXTURE_SIZE;

        if (vx >= 0 && vx < MAP_WIDTH && vy >= 0 && vy < MAP_HEIGHT) {
            GameState.tileMarker.setVisible(true);

            GameState.tileMarker.setX(((int) x / TEXTURE_SIZE) * TEXTURE_SIZE);
            GameState.tileMarker.setY(((int) y / TEXTURE_SIZE) * TEXTURE_SIZE);
        } else {
            GameState.tileMarker.setVisible(false);
        }

        return true;
    }

}
