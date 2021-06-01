package com.blacksoft.dungeon.fow.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.blacksoft.dungeon.fow.DungeonFowLayer;
import com.blacksoft.dungeon.fow.VisibilityCalculator;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;

public class UpdateFowAction extends Action {

    private VisibilityCalculator visibilityCalculator;
    private float duration = 0f;

    @Override
    public boolean act(float delta) {

        if(GameState.dungeonFowLayer == null) {
            GameState.dungeonFowLayer = new DungeonFowLayer();
            visibilityCalculator = new VisibilityCalculator(Config.MAP_WIDTH, Config.MAP_HEIGHT);
        }

        duration += delta;

        if(duration >= 0.15f) {
            // calculate visibility
            visibilityCalculator.calculateFor(new Vector2((int) GameState.party.getX() / 16, (int) GameState.party.getY() / 16), GameState.party.getVisibilityRange(), GameState.dungeon);
            duration = 0;
        }
        return false;
    }
}
