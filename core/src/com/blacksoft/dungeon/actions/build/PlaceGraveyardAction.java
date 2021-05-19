package com.blacksoft.dungeon.actions.build;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blacksoft.dungeon.actions.AbstractAction;
import com.blacksoft.dungeon.actions.ActionLevel;
import com.blacksoft.dungeon.building.Graveyard;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;
import com.blacksoft.state.UIState;

import static com.blacksoft.dungeon.actions.ActionLevel.Basic;
import static com.blacksoft.state.Config.GRAVEYARD_PRIORITY;

public class PlaceGraveyardAction extends AbstractAction {

    public static final PlaceGraveyardAction I = new PlaceGraveyardAction();

    static {

    }

    @Override
    public void draw(Batch batch,
                     float parentAlpha) {
        batch.draw(UIState.GraveyardCardImage.getRegion(), getX(), getY());
    }

    @Override
    public int getPriority() {
        return GRAVEYARD_PRIORITY;
    }

    @Override
    public ActionLevel getActionLevel() {
        return Basic;
    }

    @Override
    public TextureRegion getTexture() {
        return UIState.GraveyardCardImage.getRegion();
    }

    @Override
    public String getTitle() {
        return "Grave of a fallen soldier";
    }

    @Override
    public String getDescription() {
        return "\nPlaces a grave on the map.\n Spawns a skeleton.";
    }

    @Override
    public void execute() {
        GameState.currentBuilding = new Graveyard();
    }

    @Override
    public int getProgressAmount() {
        return Config.GRAVEYARD_PROGRESS_VALUE;
    }

    @Override
    public Class<?> getActionResultClass() {
        return Graveyard.class;
    }
}
