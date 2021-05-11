package com.blacksoft.dungeon.actions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blacksoft.dungeon.building.Graveyard;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;

import static com.blacksoft.dungeon.actions.ActionLevel.Basic;
import static com.blacksoft.state.Config.GRAVEYARD_PRIORITY;

public class PlaceGraveyardAction extends AbstractAction {

    public static final PlaceGraveyardAction I = new PlaceGraveyardAction();

    private static final Texture texture;
    private static final TextureRegion graveyardDrawable;

    static {
        texture = new Texture(Gdx.files.internal("tile/GraveYard.png"));
        graveyardDrawable = new TextureRegion(texture);
        graveyardDrawable.setRegion(48, 48, 16, 16);
    }

    @Override
    public void draw(Batch batch,
                     float parentAlpha) {
        batch.draw(graveyardDrawable, getX(), getY());
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
        return graveyardDrawable;
    }

    @Override
    public String getTitle() {
        return "Graveyard";
    }

    @Override
    public String getDescription() {
        return "Places a graveyard on the map \nor upgrades an existing one.\n Spawns a skeleton if placed on\n empty tile.";
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
