package com.blacksoft.dungeon.actions.build;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blacksoft.dungeon.actions.AbstractAction;
import com.blacksoft.dungeon.actions.ActionLevel;
import com.blacksoft.dungeon.building.BloodPool;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;
import com.blacksoft.state.UIState;

import static com.blacksoft.dungeon.actions.ActionLevel.Advanced;
import static com.blacksoft.state.Config.BLOOD_POOL_PRIORITY;

public class PlaceBloodPoolAction extends AbstractAction {

    public static final PlaceBloodPoolAction I = new PlaceBloodPoolAction();

    @Override
    public void draw(Batch batch,
                     float parentAlpha) {
        batch.draw(UIState.BloodPoolCardImage.getRegion(), getX(), getY());
    }

    @Override
    public int getPriority() {
        return BLOOD_POOL_PRIORITY;
    }

    @Override
    public ActionLevel getActionLevel() {
        return Advanced;
    }

    @Override
    public TextureRegion getTexture() {
        return UIState.BloodPoolCardImage.getRegion();
    }

    @Override
    public String getTitle() {
        return "Blood pool";
    }

    @Override
    public String getDescription() {
        return "Places a pool of blood on the map.\nAttracts vampires.";
    }

    @Override
    public void execute() {
        GameState.currentBuilding = new BloodPool();
    }

    @Override
    public int getProgressAmount() {
        return Config.BLOOD_POOL_PROGRESS_VALUE;
    }

    @Override
    public Class<?> getActionResultClass() {
        return BloodPool.class;
    }
}
