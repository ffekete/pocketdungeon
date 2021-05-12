package com.blacksoft.creature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.blacksoft.state.Config.OOZE_SALARY_REQUEST;
import static com.blacksoft.state.Config.TEXTURE_SIZE;

public class Ooze extends Creature {

    private static final Texture texture;

    static {
        texture = new Texture("creature/Ooze.png");
    }

    private float duration = 0f;
    private final Animation<TextureRegion> animation;

    public Ooze() {
        this.animation = new Animation<>(0.6f, TextureRegion.split(texture, TEXTURE_SIZE, TEXTURE_SIZE)[0]);
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
        return 2f;
    }

    @Override
    public int getSalaryRequest() {
        return OOZE_SALARY_REQUEST;
    }
}
