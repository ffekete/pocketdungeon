package com.blacksoft.ui;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class AnimatedImage extends Image
{
    protected Animation<TextureRegion> animation = null;
    private float stateTime = 0;
    private boolean looping = true;
    private boolean flip = false;

    public AnimatedImage(Animation<TextureRegion> animation) {
        super(animation.getKeyFrame(0));
        this.animation = animation;
    }

    public AnimatedImage(Animation<TextureRegion> animation, boolean looping) {
        super(animation.getKeyFrame(0));
        this.animation = animation;
        this.looping = looping;
    }

    public AnimatedImage(Animation<TextureRegion> animation, boolean looping, boolean flip) {
        super(animation.getKeyFrame(0));
        this.animation = animation;
        this.looping = looping;
        for (TextureRegion keyFrame : this.animation.getKeyFrames()) {
            keyFrame.flip(flip, false);
        }
    }

    @Override
    public void act(float delta)
    {
        stateTime += delta;
        ((TextureRegionDrawable)getDrawable()).setRegion(animation.getKeyFrame(stateTime, this.looping));
        super.act(delta);
    }
}