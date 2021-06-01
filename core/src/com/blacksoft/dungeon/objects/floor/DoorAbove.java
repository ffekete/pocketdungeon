package com.blacksoft.dungeon.objects.floor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blacksoft.dungeon.objects.AbstractMapObject;

public class DoorAbove extends AbstractMapObject {

    public boolean opened = false;
    public int x, y;
    public boolean locked = false;

    private static TextureRegion openedTextureRegion;
    private static TextureRegion closedTextureRegion;

    static {
        openedTextureRegion = new TextureRegion(new Texture(Gdx.files.internal("tile/DoorOpenedAboveObject.png")));
        closedTextureRegion = new TextureRegion(new Texture(Gdx.files.internal("tile/Transparent.png")));
    }

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
        return opened;
    }

    @Override
    public void toggleState() {
        this.opened = !this.opened;
    }

    @Override
    public TextureRegion getTextureRegion() {
        return opened ? openedTextureRegion : closedTextureRegion;
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
