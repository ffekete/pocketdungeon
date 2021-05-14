package com.blacksoft.creature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.blacksoft.state.Config.TEXTURE_SIZE;
import static com.blacksoft.state.Config.WARLOCK_MAX_HP;
import static com.blacksoft.state.Config.WARLOCK_SALARY_REQUEST;

public class Warlock extends Creature {

    public static Texture texture;

    private final Animation<TextureRegion> animation;

    static {
        texture = new Texture("creature/Warlock.png");
    }

    private float duration = 0f;

    public Warlock() {
        this.animation = new Animation<>(0.4f, TextureRegion.split(texture, TEXTURE_SIZE, TEXTURE_SIZE)[0]);
        this.hp = this.getMaxHp();
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
        return 1.5f;
    }

    @Override
    public int getSalaryRequest() {
        return WARLOCK_SALARY_REQUEST;
    }

    @Override
    public int getMaxHp() {
        return WARLOCK_MAX_HP;
    }

    @Override
    public Animation<TextureRegion> getAnimation() {
        return this.animation;
    }
}
