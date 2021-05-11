package com.blacksoft.dungeon.actions.build;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blacksoft.dungeon.actions.AbstractAction;
import com.blacksoft.dungeon.actions.ActionLevel;
import com.blacksoft.dungeon.building.Treasury;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;

import static com.blacksoft.dungeon.actions.ActionLevel.Basic;
import static com.blacksoft.state.Config.GRAVEYARD_PRIORITY;
import static com.blacksoft.state.Config.TREASURY_PRIORITY;

public class PlaceTreasuryAction extends AbstractAction {

    public static final PlaceTreasuryAction I = new PlaceTreasuryAction();

    private static final Texture texture;
    private static final TextureRegion drawable;

    static {
        texture = new Texture(Gdx.files.internal("tile/Treasury.png"));
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
        return TREASURY_PRIORITY;
    }

    @Override
    public ActionLevel getActionLevel() {
        return Basic;
    }

    @Override
    public TextureRegion getTexture() {
        return drawable;
    }

    @Override
    public String getTitle() {
        return "Treasury";
    }

    @Override
    public String getDescription() {
        return "Increases income.";
    }

    @Override
    public void execute() {
        GameState.currentBuilding = new Treasury();
    }

    @Override
    public int getProgressAmount() {
        return Config.TREASURY_PROGRESS_VALUE;
    }

    @Override
    public Class<?> getActionResultClass() {
        return Treasury.class;
    }
}
