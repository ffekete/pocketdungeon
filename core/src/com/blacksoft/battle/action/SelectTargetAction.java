package com.blacksoft.battle.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.blacksoft.creature.Creature;
import com.blacksoft.state.GameState;
import com.blacksoft.state.UIState;
import com.blacksoft.ui.AnimatedImage;

public class SelectTargetAction extends Action {

    private Creature creature;

    public SelectTargetAction(Creature creature) {
        this.creature = creature;
    }

    @Override
    public boolean act(float delta) {
        UIState.selectionMarker.setVisible(true);
        UIState.selectionMarker.toFront();
        AnimatedImage image = GameState.battleImages.get(creature);
        UIState.selectionMarker.setPosition(image.getX() + 90 + 16, image.getY() + 60 + 48);
        return true;
    }
}
