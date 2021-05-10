package com.blacksoft.creature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.blacksoft.state.Config.TEXTURE_SIZE;

public class Skeleton extends Creature {

    private static final Texture texture;

    static {
        texture = new Texture("creature/Skeleton.png");
    }

    private float duration = 0f;
    private final Animation<TextureRegion> animation;

    public Skeleton() {
        this.animation = new Animation<>(0.3f, TextureRegion.split(texture, TEXTURE_SIZE, TEXTURE_SIZE)[0]);
    }

    @Override
    public void draw(Batch batch,
                     float parentAlpha) {
        duration += Gdx.graphics.getDeltaTime();
        batch.draw(animation.getKeyFrame(duration), getX(), getY());
    }
}
