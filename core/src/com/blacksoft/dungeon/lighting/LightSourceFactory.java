package com.blacksoft.dungeon.lighting;

import box2dLight.Light;
import box2dLight.PointLight;
import com.badlogic.gdx.graphics.Color;

import static com.blacksoft.state.GameState.rayHandler;

public class LightSourceFactory {

    public static Light getBloodPoolLightSource(int x,
                                                int y) {
        return new PointLight(rayHandler, 15, new Color(1, 0.1f, 0.1f, 1f), 16, x, y);
    }

    public static Light getDungeonEntranceLightSource(int x,
                                                int y) {
        return new PointLight(rayHandler, 15, new Color(0, 1f, 1f, 1f), 16, x, y);
    }

    public static Light getGraveyardLightSource(int x,
                                                int y) {
        return new PointLight(rayHandler, 15, new Color(0, 1f, 1f, 1f), 16, x, y);
    }

    public static Light getTorchLightSource(int x,
                                                int y) {
        return new PointLight(rayHandler, 15, new Color(1f, 1f, 0f, 1f), 64, x, y);
    }

}
