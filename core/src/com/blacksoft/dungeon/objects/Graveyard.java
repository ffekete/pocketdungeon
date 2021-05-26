package com.blacksoft.dungeon.objects;

import box2dLight.Light;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blacksoft.dungeon.Tile;
import com.blacksoft.dungeon.lighting.LightSourceFactory;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;

public class Graveyard  extends AbstractMapObject {

    public int level = 1;
    private Light lightSource;

    public int x,y;

    private static TextureRegion textureRegion;

    static {
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("tile/Grave.png")));
    }

    @Override
    public void place(int x,
                      int y) {
        GameState.loopProgress += Config.GRAVEYARD_PROGRESS_VALUE;
        GameState.skeletonLimit += 1;
        this.lightSource = LightSourceFactory.getGraveyardLightSource(x / 16 * 16 + 8,y / 16 * 16 + 8);
        this.x = x / 16;
        this.y = y / 16;
    }

    @Override
    public void destroy() {
        GameState.skeletonLimit -= level;
        lightSource.dispose();
    }

    @Override
    public Tile getTile() {
        return Tile.Grave;
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
