package com.blacksoft.dungeon.building;

import box2dLight.Light;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blacksoft.dungeon.Tile;
import com.blacksoft.dungeon.actions.AbstractAction;
import com.blacksoft.dungeon.actions.build.PlaceBloodPoolAction;
import com.blacksoft.dungeon.lighting.LightSourceFactory;
import com.blacksoft.screen.UIFactory;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;
import com.blacksoft.state.UIState;

public class BloodPool implements Building {


    private static TextureRegion textureRegion;

    static {
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("tile/BloodPool.png")));
    }

    public int level = 1;
    private Light lightSource;

    @Override
    public boolean canUpgradeBy(AbstractAction action) {
        return level <= 4 && PlaceBloodPoolAction.class.isAssignableFrom(action.getClass());
    }

    @Override
    public void place(int x,
                      int y) {
        int oldProgress = GameState.loopProgress;
        GameState.loopProgress += Config.BLOOD_POOL_PROGRESS_VALUE;
        UIFactory.I.updateLabelAmount(oldProgress, GameState.loopProgress, UIState.progressLabel, "%s", null);
        GameState.vampireLimit += 0.5f;
        this.lightSource = LightSourceFactory.getBloodPoolLightSource(x / 16 * 16 + 8,y / 16 * 16 + 8);
    }

    @Override
    public void upgrade() {
        GameState.loopProgress += Config.BLOOD_POOL_PROGRESS_VALUE;
        GameState.vampireLimit += 0.5f;
        level++;
    }

    @Override
    public void destroy() {
        GameState.vampireLimit -= 0.5f;
        lightSource.dispose();
    }

    @Override
    public Tile getTile() {
        return Tile.BloodPool;
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
}
