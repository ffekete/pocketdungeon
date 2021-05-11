package com.blacksoft.dungeon.building;

import box2dLight.Light;
import com.blacksoft.dungeon.Tile;
import com.blacksoft.dungeon.actions.AbstractAction;
import com.blacksoft.dungeon.actions.PlaceBloodPoolAction;
import com.blacksoft.dungeon.actions.PlaceGraveyardAction;
import com.blacksoft.dungeon.lighting.LightSourceFactory;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;

public class BloodPool implements Building {

    public int level = 1;
    private Light lightSource;

    @Override
    public boolean canUpgradeBy(AbstractAction action) {
        return level <= 4 && PlaceBloodPoolAction.class.isAssignableFrom(action.getClass());
    }

    @Override
    public void place(int x,
                      int y) {
        GameState.loopProgress += Config.BLOOD_POOL_PROGRESS_VALUE;
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
}
