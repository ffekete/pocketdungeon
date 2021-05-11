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

import static com.blacksoft.dungeon.actions.ActionLevel.Advanced;
import static com.blacksoft.state.Config.BLOOD_POOL_PRIORITY;

public class PlaceBloodPoolAction extends AbstractAction {

    public static final PlaceBloodPoolAction I = new PlaceBloodPoolAction();

    private static final Texture texture;
    private static final TextureRegion drawable;

    static {
        texture = new Texture(Gdx.files.internal("tile/BloodPool.png"));
        drawable = new TextureRegion(texture);
        drawable.setRegion(48, 48, 16, 16);
    }

    @Override
    public void draw(Batch batch,
                     float parentAlpha) {
        batch.draw(drawable, getX(), getY());
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
        return drawable;
    }

    @Override
    public String getTitle() {
        return "Blood pool";
    }

    @Override
    public String getDescription() {
        return "Places a pool of blood on the map \nor upgrades an existing one.\nAttracts vampires.";
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
