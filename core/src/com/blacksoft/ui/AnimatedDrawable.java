package com.blacksoft.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class AnimatedDrawable extends TextureRegionDrawable
{
    protected Animation<TextureRegion> animation = null;
    private float stateTime = 0;

    public AnimatedDrawable(Animation<TextureRegion> animation) {
        super(animation.getKeyFrame(0));
        this.animation = animation;
    }



    @Override
    public void draw(Batch batch,
                     float x,
                     float y,
                     float width,
                     float height) {
        stateTime += Gdx.graphics.getDeltaTime();
        //super.draw(batch, x, y, width, height);
        batch.draw(animation.getKeyFrame(stateTime, true), x,y, width, height);
    }

    //    @Override
//    public void act(float delta)
//    {
//        stateTime += delta;
//        ((TextureRegionDrawable)getDrawable()).setRegion(animation.getKeyFrame(stateTime, true));
//        super.act(delta);
//    }
}