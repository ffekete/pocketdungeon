package com.blacksoft.creature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blacksoft.state.Config;

import static com.blacksoft.state.Config.SKELETON_MAX_HP;
import static com.blacksoft.state.Config.SKELETON_SALARY_REQUEST;
import static com.blacksoft.state.Config.TEXTURE_SIZE;

public class Skeleton extends Creature {

    public static Texture texture;

    private final Animation<TextureRegion> animation;

    static {
        texture = new Texture("creature/Skeleton.png");
    }

    private float duration = 0f;

    public Skeleton() {
        this.animation = new Animation<>(0.4f, TextureRegion.split(texture, TEXTURE_SIZE, TEXTURE_SIZE)[0]);
        this.hp = this.getMaxHp();
        this.mp = getMaxMp();
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
        return SKELETON_SALARY_REQUEST;
    }

    @Override
    public int getMaxHp() {
        return level * SKELETON_MAX_HP;
    }

    @Override
    public int getMaxMp() {
        return level * Config.SKELETON_MAX_MP;
    }

    @Override
    public Animation<TextureRegion> getAnimation() {
        return this.animation;
    }
}
