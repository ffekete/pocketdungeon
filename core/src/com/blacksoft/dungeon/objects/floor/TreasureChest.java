package com.blacksoft.dungeon.objects.floor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blacksoft.dungeon.Tile;
import com.blacksoft.dungeon.objects.AbstractMapObject;
import com.blacksoft.hero.Party;

public class TreasureChest extends AbstractMapObject {

    private static TextureRegion textureRegionClosed;
    private static TextureRegion textureRegionOpened;

    public boolean opened = false;

    static {
        textureRegionClosed = new TextureRegion(new Texture(Gdx.files.internal("object/ground/TreasureChest.png")));
        textureRegionOpened = new TextureRegion(new Texture(Gdx.files.internal("object/ground/TreasureChestOpened.png")));
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
    public void interact(Party party) {
        this.opened = true;
    }

    @Override
    public TextureRegion getTextureRegion() {
        return opened ? textureRegionOpened : textureRegionClosed;
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
