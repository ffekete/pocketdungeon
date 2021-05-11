package com.blacksoft.dungeon.building;

import box2dLight.Light;
import com.blacksoft.dungeon.Tile;
import com.blacksoft.dungeon.actions.AbstractAction;
import com.blacksoft.dungeon.actions.PlaceTorchAction;
import com.blacksoft.dungeon.lighting.FlickeringLightAction;
import com.blacksoft.dungeon.lighting.LightSourceFactory;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;

public class Torch implements Building {

    public int level = 1;
    private Light lightSource;

    @Override
    public boolean canUpgradeBy(AbstractAction action) {
        return level <= 4 && PlaceTorchAction.class.isAssignableFrom(action.getClass());
    }

    @Override
    public void place(int x,
                      int y) {
        GameState.loopProgress += Config.TORCH_PROGRESS_VALUE;
        GameState.oozeLimit
                += 0.5f;
        this.lightSource = LightSourceFactory.getTorchLightSource(x / 16 * 16 + 8,y / 16 * 16 + 8);
        GameState.stage.addAction(new FlickeringLightAction(this.lightSource));
    }

    @Override
    public void upgrade() {
        GameState.loopProgress += Config.TORCH_PROGRESS_VALUE;
        GameState.oozeLimit += 0.5f;
        level++;
    }

    @Override
    public void destroy() {
        GameState.oozeLimit -= 0.5f;
        lightSource.dispose();
    }

    @Override
    public Tile getTile() {
        return Tile.Torch;
    }

    @Override
    public int getUpgradeLevel() {
        return level;
    }
}
