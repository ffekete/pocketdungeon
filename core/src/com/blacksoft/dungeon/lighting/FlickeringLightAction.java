package com.blacksoft.dungeon.lighting;

import box2dLight.Light;
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
            light.setDistance(originalDistance + new Random().nextFloat() * 8);
            period = 0;
        }
        return false;
    }

    public void setOriginalDistance(float originalDistance) {
        this.originalDistance = originalDistance;
    }
}
