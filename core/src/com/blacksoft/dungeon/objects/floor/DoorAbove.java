package com.blacksoft.dungeon.objects.floor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blacksoft.dungeon.objects.AbstractMapObject;
import com.blacksoft.hero.Party;

public class DoorAbove extends AbstractMapObject {

    public boolean opened = false;
    public int x, y;
    public boolean locked = false;

    private static TextureRegion openedTextureRegion;
    private static TextureRegion closedTextureRegion;

    static {
        openedTextureRegion = new TextureRegion(new Texture(Gdx.files.internal("object/ground/DoorOpenedAboveObject.png")));
        closedTextureRegion = new TextureRegion(new Texture(Gdx.files.internal("object/ground/Transparent.png")));
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
    public void interact(Party party) {
        this.opened = true;


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
