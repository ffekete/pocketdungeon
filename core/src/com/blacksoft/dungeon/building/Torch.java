package com.blacksoft.dungeon.building;

import box2dLight.Light;
import com.blacksoft.dungeon.Tile;
import com.blacksoft.dungeon.actions.AbstractAction;
import com.blacksoft.dungeon.actions.build.PlaceTorchAction;
import com.blacksoft.dungeon.lighting.FlickeringLightAction;
import com.blacksoft.dungeon.lighting.LightSourceFactory;
import com.blacksoft.screen.UIFactory;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;
import com.blacksoft.state.UIState;

public class Torch implements Building {

    public int level = 1;
    private Light lightSource;
    private FlickeringLightAction flickeringLightAction;

    @Override
    public boolean canUpgradeBy(AbstractAction action) {
        return level < 5 && PlaceTorchAction.class.isAssignableFrom(action.getClass());
    }

    @Override
    public void place(int x,
                      int y) {
        int oldProgress = GameState.loopProgress;
        GameState.loopProgress += Config.TORCH_PROGRESS_VALUE;
        UIFactory.I.updateLabelAmount(oldProgress, GameState.loopProgress, UIState.progressLabel, "%s", null);

        GameState.oozeLimit
                += 0.5f;
        this.lightSource = LightSourceFactory.getTorchLightSource(x / 16 * 16 + 8, y / 16 * 16 + 8);
        this.flickeringLightAction = new FlickeringLightAction(this.lightSource);
        GameState.stage.addAction(flickeringLightAction);
    }

    @Override
    public void upgrade() {
        GameState.loopProgress += Config.TORCH_PROGRESS_VALUE;
        GameState.oozeLimit += 0.5f;
        level++;

        flickeringLightAction.setOriginalDistance(lightSource.getDistance() + level * 5);
    }

    @Override
    public void destroy() {
        GameState.oozeLimit -= 0.5f;
        lightSource.setActive(false);
        lightSource.remove();
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
