package com.blacksoft.creature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blacksoft.state.Config;

import static com.blacksoft.state.Config.SKELETON_MAX_HP;
import static com.blacksoft.state.Config.TEXTURE_SIZE;

public class Skeleton extends Creature {

    public static Texture walkAnimationTexture;
    public static Texture idleAnimationTexture;

    private final Animation<TextureRegion> walkAnimation;
    private final Animation<TextureRegion> idleAnimation;

    static {
        walkAnimationTexture = new Texture("creature/SkeletonWalk.png");
        idleAnimationTexture = new Texture("creature/SkeletonIdle.png");
    }

    private float duration = 0f;

    public Skeleton() {
        this.walkAnimation = new Animation<>(0.1f, TextureRegion.split(walkAnimationTexture, TEXTURE_SIZE, TEXTURE_SIZE)[0]);
        this.idleAnimation = new Animation<>(0.5f, TextureRegion.split(idleAnimationTexture, TEXTURE_SIZE, TEXTURE_SIZE)[0]);
        this.hp = this.getMaxHp();
        this.mp = getMaxMp();
    }

    @Override
    public void draw(Batch batch,
                     float parentAlpha) {

        duration += Gdx.graphics.getDeltaTime();
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);

        TextureRegion textureRegion;
        if(state == State.Idle) {
            textureRegion = idleAnimation.getKeyFrame(duration, true);
        } else {
            textureRegion = walkAnimation.getKeyFrame(duration, true);
        }

        if(direction == Direction.Left && !textureRegion.isFlipX()) {
            textureRegion.flip(true, false);
        } else if (direction == Direction.Right && textureRegion.isFlipX()){
            textureRegion.flip(true, false);
        }

        batch.draw(textureRegion, getX(), getY());
        batch.setColor(color);
    }

    @Override
    public float getSpeed() {
        return 1.5f;
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
        return this.idleAnimation;
    }

    @Override
    public int getMeleeDamage() {
        return 3 + (level / 3);
    }

    @Override
    public int getMeleeDefence() {
        return 2 + tempDefenceModifier;
    }
}
