package com.blacksoft.dungeon.actions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import static com.blacksoft.dungeon.actions.ActionLevel.Basic;
import static com.blacksoft.state.Config.GRAVEYARD_PRIORITY;

public class PlaceGraveyardAction extends AbstractAction {

    public static final PlaceGraveyardAction I = new PlaceGraveyardAction();

    private static final Texture texture;

    static {
        texture = new Texture(Gdx.files.internal("tile/GraveYard.png"));
    }

    @Override
    public void draw(Batch batch,
                     float parentAlpha) {
        batch.draw(texture, getX(), getY());
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
    public Texture getTexture() {
        return this.texture;
    }

    @Override
    public String getTitle() {
        return "Graveyard";
    }

    @Override
    public String getDescription() {
        return "Places a graveyard on the map \nor upgrades an existing one.\n Spawns a skeleton if placed on\n empty tile.";
    }
}
