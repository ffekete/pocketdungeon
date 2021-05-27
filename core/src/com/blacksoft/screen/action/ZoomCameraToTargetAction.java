package com.blacksoft.screen.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.blacksoft.state.GameState;

public class ZoomCameraToTargetAction extends Action {

    public static final float ZOOM_SPEED = 0.01f;
    public float duration = 0f;

    @Override
    public boolean act(float delta) {

        duration += delta;

        if(duration >= 0.01f) {

            if (Math.abs(GameState.orthographicCamera.zoom - ZOOM_SPEED) < 0.5f) {
                GameState.orthographicCamera.zoom = 0.5f;
                return true;
            } else {
                if (GameState.orthographicCamera.zoom > 0.5f) {
                    GameState.orthographicCamera.zoom -= ZOOM_SPEED;
                } else {
                    GameState.orthographicCamera.zoom += ZOOM_SPEED;
                }
            }
            duration = 0;
        }

        return false;
    }
}
