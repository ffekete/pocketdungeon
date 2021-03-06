package com.blacksoft.dungeon.objects.wall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blacksoft.dungeon.objects.AbstractMapObject;
import com.blacksoft.hero.Party;

public class Banner01 extends AbstractMapObject {

    private static TextureRegion textureRegion;

    static {
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("object/wall/Banner01.png")));
    }

    public int x, y;

    @Override
    public void place(int x,
                      int y) {
        this.x = x / 16;
        this.y = y / 16;
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
