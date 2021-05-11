package com.blacksoft.dungeon.lighting;

import box2dLight.Light;
import box2dLight.RayHandler;
import com.badlogic.gdx.scenes.scene2d.Action;

import java.util.Random;

public class FlickeringLightAction extends Action {

    private Light light;
    private float originalDistance;
    private float period = 0f;

    public FlickeringLightAction(Light light) {
        this.light = light;
        this.originalDistance = light.getDistance();
    }


    @Override
    public boolean act(float delta) {
        period += delta;

        if(period > 0.25f) {
            light.setDistance(originalDistance + new Random().nextFloat() * 3);
            period = 0;
        }
        return false;
    }
}
