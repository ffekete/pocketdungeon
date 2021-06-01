package com.blacksoft.hero;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blacksoft.creature.Direction;
import com.blacksoft.creature.State;
import com.blacksoft.state.Config;

import static com.blacksoft.state.Config.TEXTURE_SIZE;

public class Wizard extends Hero {

    public static Texture walkingAnimationTexture;
    public static Texture walkingUpAnimationTexture;
    public static Texture walkingDownAnimationTexture;
    public static Texture idleAnimationTexture;

    private final Animation<TextureRegion> walkAnimation;
    private final Animation<TextureRegion> walkUpAnimation;
    private final Animation<TextureRegion> walkDownAnimation;
    private final Animation<TextureRegion> idleAnimation;

    static {
        walkingAnimationTexture = new Texture("hero/Wizard_walk_right.png");
        walkingUpAnimationTexture = new Texture("hero/Wizard_walk_up.png");
        walkingDownAnimationTexture = new Texture("hero/Wizard_walk_down.png");
        idleAnimationTexture = new Texture("hero/Wizard_idle.png");
    }

    public Wizard(Party party) {
        super(party);
        this.hp = getMaxHp();
        this.mp = getMaxMp();
        walkAnimation = new Animation<>(0.15f, TextureRegion.split(walkingAnimationTexture, TEXTURE_SIZE, TEXTURE_SIZE)[0]);
        walkUpAnimation = new Animation<>(0.175f, TextureRegion.split(walkingUpAnimationTexture, TEXTURE_SIZE, TEXTURE_SIZE)[0]);
        walkDownAnimation = new Animation<>(0.175f, TextureRegion.split(walkingDownAnimationTexture, TEXTURE_SIZE, TEXTURE_SIZE)[0]);
        idleAnimation = new Animation<>(0.2f, TextureRegion.split(idleAnimationTexture, TEXTURE_SIZE, TEXTURE_SIZE)[0]);
    }

    @Override
    public float getSpeed() {
        return 0.6f;
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
            if(direction == Direction.Left || direction == Direction.Right) {
                textureRegion = walkAnimation.getKeyFrame(duration, true);
            } else if (direction == Direction.Up){
                textureRegion = walkUpAnimation.getKeyFrame(duration, true);
            } else {
                textureRegion = walkDownAnimation.getKeyFrame(duration, true);
            }
        }

        if(direction == Direction.Left && !textureRegion.isFlipX()) {
            textureRegion.flip(true, false);
        } else if (direction == Direction.Right && textureRegion.isFlipX()){
            textureRegion.flip(true, false);
        }

        batch.draw(textureRegion, getX(), getY() + 4f);

        batch.setColor(color);
    }

    @Override
    public int getMaxHp() {
        return Config.WIZARD_MAX_HP;
    }

    @Override
    public int getMaxMp() {
        return Config.WIZARD_MAX_MP;
    }

    @Override
    public Animation<TextureRegion> getAnimation() {
        return this.walkAnimation;
    }

    @Override
    public int getMeleeDamage() {
        return 1 + level / 5;
    }

    @Override
    public int getMeleeDefence() {
        return level / 5 + tempDefenceModifier;
    }
}
