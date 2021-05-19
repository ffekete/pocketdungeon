package com.blacksoft.battle.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.blacksoft.creature.Creature;
import com.blacksoft.state.GameState;
import com.blacksoft.state.UIState;
import com.blacksoft.ui.AnimatedImage;

public class RemoveTargetSelectionAction extends Action {

    @Override
    public boolean act(float delta) {
        UIState.selectionMarker.setVisible(false);
        return true;
    }
}
