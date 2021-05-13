package com.blacksoft.dungeon.actions.build;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blacksoft.dungeon.actions.AbstractAction;
import com.blacksoft.dungeon.actions.ActionLevel;
import com.blacksoft.dungeon.building.Graveyard;
import com.blacksoft.dungeon.building.Library;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;

import static com.blacksoft.dungeon.actions.ActionLevel.Advanced;
import static com.blacksoft.dungeon.actions.ActionLevel.Basic;
import static com.blacksoft.state.Config.GRAVEYARD_PRIORITY;
import static com.blacksoft.state.Config.LIBRARY_PRIORITY;
import static com.blacksoft.state.Config.LIBRARY_PROGRESS_VALUE;

public class PlaceLibraryAction extends AbstractAction {

    public static final PlaceLibraryAction I = new PlaceLibraryAction();

    private static final Texture texture;
    private static final TextureRegion drawable;

    static {
        texture = new Texture(Gdx.files.internal("tile/Library.png"));
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
        return LIBRARY_PRIORITY;
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
        return "Library";
    }

    @Override
    public String getDescription() {
        return "Libraries are increasing research.\nAttracts warlocks.";
    }

    @Override
    public void execute() {
        GameState.currentBuilding = new Library();
    }

    @Override
    public int getProgressAmount() {
        return LIBRARY_PROGRESS_VALUE;
    }

    @Override
    public Class<?> getActionResultClass() {
        return Library.class;
    }
}
