package com.blacksoft.battle.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.blacksoft.battle.BattleScreenInitializer;
import com.blacksoft.creature.Creature;
import com.blacksoft.state.GameState;
import com.blacksoft.state.UIState;
import com.blacksoft.ui.AnimatedImage;

import static com.blacksoft.state.Config.BATTLE_SCREEN_POS_X;
import static com.blacksoft.state.Config.BATTLE_SCREEN_POS_Y;

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
        UIState.selectionMarker.setPosition(image.getX() + BATTLE_SCREEN_POS_X + BattleScreenInitializer.HERO_CELL_WIDTH / 3, image.getY() + BATTLE_SCREEN_POS_Y + BattleScreenInitializer.HERO_CELL_WIDTH);
        return true;
    }
}
