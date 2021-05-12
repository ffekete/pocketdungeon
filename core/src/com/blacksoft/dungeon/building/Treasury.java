package com.blacksoft.dungeon.building;

import box2dLight.Light;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blacksoft.dungeon.Tile;
import com.blacksoft.dungeon.actions.AbstractAction;
import com.blacksoft.dungeon.actions.build.PlaceGraveyardAction;
import com.blacksoft.dungeon.actions.build.PlaceTreasuryAction;
import com.blacksoft.dungeon.lighting.LightSourceFactory;
import com.blacksoft.screen.UIFactory;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;
import com.blacksoft.state.UIState;

public class Treasury implements Building {


    private static TextureRegion textureRegion;

    static {
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("tile/Treasury.png")));
    }

    public int level = 1;
    private Light lightSource;

    @Override
    public boolean canUpgradeBy(AbstractAction action) {
        return level <= 4 && PlaceTreasuryAction.class.isAssignableFrom(action.getClass());
    }

    @Override
    public void place(int x,
                      int y) {
        int oldProgress = GameState.loopProgress;
        GameState.loopProgress += Config.TREASURY_PROGRESS_VALUE;
        UIFactory.I.updateLabelAmount(oldProgress, GameState.loopProgress, UIState.progressLabel, "%s", null);
        this.lightSource = LightSourceFactory.getGraveyardLightSource(x / 16 * 16 + 8,y / 16 * 16 + 8);
        GameState.maxGoldCapacity += 500;
    }

    @Override
    public void upgrade() {
        GameState.loopProgress += Config.TREASURY_PROGRESS_VALUE;
        GameState.maxGoldCapacity += 500;
        level++;
    }

    @Override
    public void destroy() {
        lightSource.dispose();
        GameState.maxGoldCapacity -= 500 * level;
    }

    @Override
    public Tile getTile() {
        return Tile.Treasury;
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
