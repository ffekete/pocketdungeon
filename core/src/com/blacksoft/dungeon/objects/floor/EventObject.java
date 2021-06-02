package com.blacksoft.dungeon.objects.floor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blacksoft.battle.BattleInitializer;
import com.blacksoft.creature.Skeleton;
import com.blacksoft.dungeon.objects.AbstractMapObject;
import com.blacksoft.hero.Party;
import com.blacksoft.state.GameState;

import java.lang.reflect.Array;
import java.util.Arrays;

import static com.blacksoft.state.Config.TEXTURE_SIZE;

public class EventObject extends AbstractMapObject {

    private static TextureRegion textureRegion;
    private static Animation<TextureRegion> animation;
    private float duration = 0f;
    private boolean disabled = false;

    static {
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("object/ground/Event.png")));
        animation = new Animation<>(0.15f, TextureRegion.split(textureRegion.getTexture(), TEXTURE_SIZE, TEXTURE_SIZE)[0]);
    }

    public int x, y;

    @Override
    public void place(int x,
                      int y) {
        super.blocking = false;
    }

    @Override
    public void destroy() {
    }

    @Override
    public boolean getState() {
        return false;
    }

    @Override
    public void interact(Party party) {
        if(!disabled) {
            BattleInitializer.init(Arrays.asList(new Skeleton()), GameState.party);
            disabled = true;
        }
    }

    @Override
    public TextureRegion getTextureRegion() {
        duration += Gdx.graphics.getDeltaTime();
        return animation.getKeyFrame(duration, true);
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }
}
