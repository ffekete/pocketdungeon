package com.blacksoft.dungeon.objects.floor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blacksoft.dungeon.objects.AbstractMapObject;

public class Gutter01 extends AbstractMapObject {

    private static TextureRegion textureRegionClosed;

    static {
        textureRegionClosed = new TextureRegion(new Texture(Gdx.files.internal("tile/Gutter.png")));
    }

    public int x, y;

    @Override
    public void place(int x,
                      int y) {

    }

    @Override
    public void destroy() {
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
        return textureRegionClosed;
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