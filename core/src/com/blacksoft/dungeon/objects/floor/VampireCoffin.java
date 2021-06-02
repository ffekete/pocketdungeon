package com.blacksoft.dungeon.objects.floor;

import box2dLight.Light;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blacksoft.dungeon.Tile;
import com.blacksoft.dungeon.lighting.LightSourceFactory;
import com.blacksoft.dungeon.objects.AbstractMapObject;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;

public class VampireCoffin extends AbstractMapObject {

    private static TextureRegion textureRegion;

    public int x, y;

    static {
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("object/ground/MysteriousCoffin.png")));
    }

    public int level = 1;
    private Light lightSource;

    @Override
    public void place(int x,
                      int y) {
        this.lightSource = LightSourceFactory.getBloodPoolLightSource(x / 16 * 16 + 8, y / 16 * 16 + 8);
        this.x = x / 16;
        this.y = y / 16;
    }

    @Override
    public void destroy() {
        lightSource.dispose();
    }

    @Override
    public boolean getState() {
        return false;
    }

    @Override
    public void toggleState() {

    }

    @Override
    public TextureRegion getTextureRegion() {
        return textureRegion;
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
