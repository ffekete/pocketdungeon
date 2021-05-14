package com.blacksoft.dungeon.building;

import box2dLight.Light;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blacksoft.dungeon.Tile;
import com.blacksoft.dungeon.actions.AbstractAction;
import com.blacksoft.dungeon.lighting.LightSourceFactory;

public class DungeonEntrance implements Building {

    public int x,y;
    public int level = 1;
    private Light lightSource;

    private static TextureRegion textureRegion;

    static {
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("tile/DungeonEntrance.png")));
    }

    @Override
    public boolean canUpgradeBy(AbstractAction action) {
        return false;
    }

    @Override
    public void place(int x,
                      int y) {
        this.lightSource = LightSourceFactory.getDungeonEntranceLightSource(x / 16 * 16 + 8,y / 16 * 16 + 8);
        this.x = x / 16;
        this.y = y / 16;
    }

    @Override
    public void upgrade() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public Tile getTile() {
        return Tile.GraveYard;
    }

    @Override
    public int getUpgradeLevel() {
        return level;
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
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}
