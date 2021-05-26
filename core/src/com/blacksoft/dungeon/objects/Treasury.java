package com.blacksoft.dungeon.objects;

import box2dLight.Light;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blacksoft.dungeon.Tile;
import com.blacksoft.dungeon.lighting.LightSourceFactory;
import com.blacksoft.screen.UIFactory;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;
import com.blacksoft.state.UIState;

public class Treasury extends AbstractMapObject {


    private static TextureRegion textureRegion;

    static {
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("tile/Treasury.png")));
    }

    public int x, y;
    public int level = 1;
    private Light lightSource;

    @Override
    public void place(int x,
                      int y) {

        GameState.loopProgress += Config.TREASURY_PROGRESS_VALUE;
        this.lightSource = LightSourceFactory.getGraveyardLightSource(x / 16 * 16 + 8, y / 16 * 16 + 8);
        int old = GameState.gold;
        GameState.gold += 500;
        UIFactory.I.updateLabelAmount(old, GameState.gold, UIState.goldLabel, "%s", null);
        this.x = x / 16;
        this.y = y / 16;
    }

    @Override
    public void destroy() {
        lightSource.dispose();
    }

    @Override
    public Tile getTile() {
        return Tile.Treasury;
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
