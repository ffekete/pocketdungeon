package com.blacksoft.dungeon.building;

import com.blacksoft.dungeon.Tile;
import com.blacksoft.dungeon.actions.AbstractAction;
import com.blacksoft.dungeon.actions.PlaceGraveyardAction;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;

public class Graveyard implements Building {

    public int level = 1;

    @Override
    public boolean canUpgradeBy(AbstractAction action) {
        return level <= 4 && PlaceGraveyardAction.class.isAssignableFrom(action.getClass());
    }

    @Override
    public void place(int x,
                      int y) {
        GameState.loopProgress += Config.GRAVEYARD_PROGRESS_VALUE;
        // spawn skeleton
    }

    @Override
    public void upgrade() {
        GameState.loopProgress += Config.GRAVEYARD_PROGRESS_VALUE;
        level++;
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
}
