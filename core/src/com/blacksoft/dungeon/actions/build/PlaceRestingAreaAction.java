package com.blacksoft.dungeon.actions.build;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blacksoft.dungeon.actions.AbstractAction;
import com.blacksoft.dungeon.actions.ActionLevel;
import com.blacksoft.dungeon.building.RestingArea;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;
import com.blacksoft.state.UIState;

import static com.blacksoft.dungeon.actions.ActionLevel.Basic;
import static com.blacksoft.state.Config.RESTING_AREA_PRIORITY;

public class PlaceRestingAreaAction extends AbstractAction {

    public static final PlaceRestingAreaAction I = new PlaceRestingAreaAction();

    @Override
    public void draw(Batch batch,
                     float parentAlpha) {
        batch.draw(UIState.RestingAreaCardImage.getRegion(), getX(), getY());
    }

    @Override
    public int getPriority() {
        return RESTING_AREA_PRIORITY;
    }

    @Override
    public ActionLevel getActionLevel() {
        return Basic;
    }

    @Override
    public TextureRegion getTexture() {
        return UIState.RestingAreaCardImage.getRegion();
    }

    @Override
    public String getTitle() {
        return "Resting area";
    }

    @Override
    public String getDescription() {
        return "Creatures can rest here when \ntheir HP or MP is down.";
    }

    @Override
    public void execute() {
        GameState.currentBuilding = new RestingArea();
    }

    @Override
    public int getProgressAmount() {
        return Config.RESTING_AREA_PROGRESS_VALUE;
    }

    @Override
    public Class<?> getActionResultClass() {
        return RestingArea.class;
    }
}
