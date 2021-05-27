package com.blacksoft.screen.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.blacksoft.state.GameState;

public class ContinuousFollowCameraAction extends Action {

    public static final float CAMERA_MOVE_SPEED = 2.5f;

    @Override
    public boolean act(float delta) {

        if(GameState.party.getX() > GameState.orthographicCamera.position.x) {

            if(Math.abs(GameState.party.getX() - GameState.orthographicCamera.position.x) < CAMERA_MOVE_SPEED) {
                GameState.orthographicCamera.position.x = GameState.party.getX();
            } else {
                GameState.orthographicCamera.position.x += CAMERA_MOVE_SPEED;
            }
        }

        else if(GameState.party.getX() < GameState.orthographicCamera.position.x) {

            if(Math.abs(GameState.party.getX() - GameState.orthographicCamera.position.x) < CAMERA_MOVE_SPEED) {
                GameState.orthographicCamera.position.x = GameState.party.getX();
            } else {
                GameState.orthographicCamera.position.x -= CAMERA_MOVE_SPEED;
            }
        }

        if(GameState.party.getY() > GameState.orthographicCamera.position.y) {

            if(Math.abs(GameState.party.getY() - GameState.orthographicCamera.position.y) < CAMERA_MOVE_SPEED) {
                GameState.orthographicCamera.position.y = GameState.party.getY();
            } else {
                GameState.orthographicCamera.position.y += CAMERA_MOVE_SPEED;
            }
        }

        else if(GameState.party.getY() < GameState.orthographicCamera.position.y) {

            if(Math.abs(GameState.party.getY() - GameState.orthographicCamera.position.y) < CAMERA_MOVE_SPEED) {
                GameState.orthographicCamera.position.y = GameState.party.getY();
            } else {
                GameState.orthographicCamera.position.y -= CAMERA_MOVE_SPEED;
            }
        }

        GameState.orthographicCamera.update();

        return false;
    }
}
