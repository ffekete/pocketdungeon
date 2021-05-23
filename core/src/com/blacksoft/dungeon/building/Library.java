package com.blacksoft.dungeon.building;

import box2dLight.Light;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blacksoft.dungeon.Tile;
import com.blacksoft.dungeon.lighting.LightSourceFactory;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;

public class Library implements Building {

    public int level = 1;
    private Light lightSource;

    public int x,y;

    private static TextureRegion textureRegion;

    static {
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("tile/Library.png")));
    }

    @Override
    public void place(int x,
                      int y) {

        GameState.loopProgress += Config.LIBRARY_PROGRESS_VALUE;
        GameState.warlockLimit += 1;
        this.lightSource = LightSourceFactory.getLibraryLightSource(x / 16 * 16 + 8,y / 16 * 16 + 8);
        this.x = x / 16;
        this.y = y / 16;
    }

    @Override
    public void destroy() {
        GameState.warlockLimit -= level;
        lightSource.dispose();
    }

    @Override
    public Tile getTile() {
        return Tile.Library;
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
