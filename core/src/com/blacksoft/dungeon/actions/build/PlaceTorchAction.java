package com.blacksoft.dungeon.actions.build;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blacksoft.dungeon.actions.AbstractAction;
import com.blacksoft.dungeon.actions.ActionLevel;
import com.blacksoft.dungeon.building.Torch;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;
import com.blacksoft.state.UIState;

import static com.blacksoft.dungeon.actions.ActionLevel.Basic;
import static com.blacksoft.state.Config.TORCH_PRIORITY;

public class PlaceTorchAction extends AbstractAction {

    public static final PlaceTorchAction I = new PlaceTorchAction();

    @Override
    public void draw(Batch batch,
                     float parentAlpha) {
        batch.draw(UIState.TorchCardImage.getRegion(), getX(), getY());
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
        return UIState.TorchCardImage.getRegion();
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

    @Override
    public Class<?> getActionResultClass() {
        return Torch.class;
    }
}
