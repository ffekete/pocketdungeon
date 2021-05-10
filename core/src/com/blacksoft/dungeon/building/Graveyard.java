package com.blacksoft.dungeon.building;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.blacksoft.creature.Creature;
import com.blacksoft.creature.Skeleton;
import com.blacksoft.creature.action.WanderingAction;
import com.blacksoft.dungeon.Dungeon;
import com.blacksoft.dungeon.Tile;
import com.blacksoft.dungeon.actions.AbstractAction;
import com.blacksoft.dungeon.actions.PlaceGraveyardAction;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;

import static com.blacksoft.state.Config.TEXTURE_SIZE;

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
        Creature skeleton = new Skeleton();
        GameState.stage.addActor(skeleton);
        GameState.creatures.add(skeleton);
        skeleton.setPosition(x / TEXTURE_SIZE * TEXTURE_SIZE,y / TEXTURE_SIZE * TEXTURE_SIZE);
        skeleton.addAction(new WanderingAction());
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
