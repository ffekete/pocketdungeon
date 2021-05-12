package com.blacksoft.screen.input;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.blacksoft.dungeon.Tile;
import com.blacksoft.dungeon.building.Gate;
import com.blacksoft.state.GameState;
import com.blacksoft.state.UIState;

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

            if (GameState.dungeon.nodes[vx][vy].tile == Tile.GateOpened || GameState.dungeon.nodes[vx][vy].tile == Tile.GateClosed) {
                if (((Gate) GameState.dungeon.nodes[vx][vy].building).locked) {
                    UIState.closedLockImage.setVisible(true);
                    UIState.closedLockImage.setX(((int) x / TEXTURE_SIZE) * TEXTURE_SIZE);
                    UIState.closedLockImage.setY(((int) y / TEXTURE_SIZE) * TEXTURE_SIZE);
                } else {
                    UIState.openLockImage.setVisible(true);
                    UIState.openLockImage.setX(((int) x / TEXTURE_SIZE) * TEXTURE_SIZE);
                    UIState.openLockImage.setY(((int) y / TEXTURE_SIZE) * TEXTURE_SIZE);
                }
            } else {
                UIState.closedLockImage.setVisible(false);
                UIState.openLockImage.setVisible(false);
            }
        } else {
            GameState.tileMarker.setVisible(false);
            UIState.closedLockImage.setVisible(false);
            UIState.openLockImage.setVisible(false);
        }


        return true;
    }

}
