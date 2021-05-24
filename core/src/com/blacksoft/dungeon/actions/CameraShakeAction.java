package com.blacksoft.dungeon.actions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.blacksoft.state.GameState;

import java.util.Random;

public class CameraShakeAction extends TemporalAction {

    Vector3 cameraPosition;
    private float offsetX = 2f;
    private float offsetY = 1f;

    private float duration = 0f;

    public CameraShakeAction(float duration) {
        super(duration);
    }

    @Override
    protected void begin() {
        super.begin();
        this.cameraPosition = new Vector3();
        this.cameraPosition.x = GameState.orthographicCamera.position.x;
        this.cameraPosition.y = GameState.orthographicCamera.position.y;
    }

    @Override
    protected void end() {
        super.end();
        GameState.orthographicCamera.position.x = cameraPosition.x;
        GameState.orthographicCamera.position.y = cameraPosition.y;
        System.out.println("finished");
        GameState.orthographicCamera.update();
    }

    @Override
    protected void update(float percent) {
        duration += Gdx.graphics.getDeltaTime();
        if(duration >= 0.01f) {
            offsetX = -1 * offsetX;
            offsetY = -1 * offsetY;
            GameState.orthographicCamera.translate(offsetX, offsetY);
            GameState.orthographicCamera.update();
            duration = 0f;
        }
    }
}
