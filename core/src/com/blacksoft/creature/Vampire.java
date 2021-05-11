package com.blacksoft.creature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.blacksoft.state.Config.TEXTURE_SIZE;

public class Vampire extends Creature {

    private static final Texture texture;

    static {
        texture = new Texture("creature/Vampire.png");
    }

    private float duration = 0f;
    private final Animation<TextureRegion> animation;

    public Vampire() {
        this.animation = new Animation<>(0.4f, TextureRegion.split(texture, TEXTURE_SIZE, TEXTURE_SIZE)[0]);
    }

    @Override
    public void draw(Batch batch,
                     float parentAlpha) {
        duration += Gdx.graphics.getDeltaTime();
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);

        batch.draw(animation.getKeyFrame(duration, true), getX(), getY());
        batch.setColor(color);
    }

    @Override
    public float getSpeed() {
        return 1f;
    }
}
