package com.blacksoft.dungeon.lighting;

import box2dLight.Light;
import box2dLight.PointLight;
import com.badlogic.gdx.graphics.Color;

import static com.blacksoft.state.GameState.rayHandler;

public class LightSourceFactory {

    public static Light getGraveyardLightSource(int x,
                                                int y) {
        return new PointLight(rayHandler, 15, new Color(0, 0.7f, 0.7f, 0.75f), 16, x, y);
    }

    public static Light getTorchLightSource(int x,
                                                int y) {
        return new PointLight(rayHandler, 15, new Color(0.7f, 0.7f, 0f, 0.75f), 16, x, y);
    }

}
