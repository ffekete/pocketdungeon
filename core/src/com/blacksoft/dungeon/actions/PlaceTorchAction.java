package com.blacksoft.dungeon.actions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blacksoft.dungeon.building.Graveyard;
import com.blacksoft.dungeon.building.Torch;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;

import static com.blacksoft.dungeon.actions.ActionLevel.Basic;
import static com.blacksoft.state.Config.GRAVEYARD_PRIORITY;
import static com.blacksoft.state.Config.TORCH_PRIORITY;

public class PlaceTorchAction extends AbstractAction {

    public static final PlaceTorchAction I = new PlaceTorchAction();

    private static final Texture texture;
    private static final TextureRegion torchDrawable;

    static {
        texture = new Texture(Gdx.files.internal("tile/Torch.png"));
        torchDrawable = new TextureRegion(texture);
        torchDrawable.setRegion(48, 48, 16, 16);
    }

    @Override
    public void draw(Batch batch,
                     float parentAlpha) {
        batch.draw(torchDrawable, getX(), getY());
    }

    @Override
    public int getPriority() {
        return TORCH_PRIORITY;
    }

    @Override
    public ActionLevel getActionLevel() {
        return Basic;
    }

    @Override
    public TextureRegion getTexture() {
        return torchDrawable;
    }

    @Override
    public String getTitle() {
        return "Torch";
    }

    @Override
    public String getDescription() {
        return "Emits light on the map.\nAttracts oozes.";
    }

    @Override
    public void execute() {
        GameState.currentBuilding = new Torch();
    }

    @Override
    public int getProgressAmount() {
        return Config.TORCH_PROGRESS_VALUE;
    }
}
