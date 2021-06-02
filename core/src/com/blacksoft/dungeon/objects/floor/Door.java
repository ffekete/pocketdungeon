package com.blacksoft.dungeon.objects.floor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blacksoft.dungeon.Dungeon;
import com.blacksoft.dungeon.actions.TileTypeDetector;
import com.blacksoft.dungeon.objects.AbstractMapObject;
import com.blacksoft.hero.Party;
import com.blacksoft.state.GameState;

public class Door extends AbstractMapObject {

    public boolean opened = false;
    public int x, y;
    public boolean locked = false;

    private static TextureRegion openedTextureRegion;
    private static TextureRegion closedTextureRegion;

    static {
        openedTextureRegion = new TextureRegion(new Texture(Gdx.files.internal("object/ground/Transparent.png")));
        closedTextureRegion = new TextureRegion(new Texture(Gdx.files.internal("object/ground/DoorClosed.png")));
    }

    @Override
    public void place(int x,
                      int y) {
        super.blocking = false;
        this.x = x;
        this.y = y;
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

        DoorAbove doorAbove = TileTypeDetector.getObject(GameState.dungeon, Dungeon.ABOVE_LAYER, x / 16, y / 16, DoorAbove.class);
        doorAbove.opened = true;
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
