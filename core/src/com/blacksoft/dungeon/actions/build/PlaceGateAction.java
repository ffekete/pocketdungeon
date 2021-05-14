package com.blacksoft.dungeon.actions.build;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blacksoft.dungeon.actions.AbstractAction;
import com.blacksoft.dungeon.actions.ActionLevel;
import com.blacksoft.dungeon.building.Gate;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;
import com.blacksoft.state.UIState;

import static com.blacksoft.dungeon.actions.ActionLevel.Basic;
import static com.blacksoft.state.Config.GATE_PRIORITY;

public class PlaceGateAction extends AbstractAction {

    public static final PlaceGateAction I = new PlaceGateAction();

    @Override
    public void draw(Batch batch,
                     float parentAlpha) {
        batch.draw(UIState.DoorCardImage.getRegion(), getX(), getY());
    }

    @Override
    public int getPriority() {
        return GATE_PRIORITY;
    }

    @Override
    public ActionLevel getActionLevel() {
        return Basic;
    }

    @Override
    public TextureRegion getTexture() {
        return UIState.DoorCardImage.getRegion();
    }

    @Override
    public String getTitle() {
        return "Door";
    }

    @Override
    public String getDescription() {
        return "Can be locked to shut the door to\nadventurers.";
    }

    @Override
    public void execute() {
        GameState.currentBuilding = new Gate();
    }

    @Override
    public int getProgressAmount() {
        return Config.GATE_PROGRESS_VALUE;
    }

    @Override
    public Class<?> getActionResultClass() {
        return Gate.class;
    }
}
