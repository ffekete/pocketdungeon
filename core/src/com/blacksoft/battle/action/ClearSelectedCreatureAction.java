package com.blacksoft.battle.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.blacksoft.state.GameState;

public class ClearSelectedCreatureAction extends Action {

    @Override
    public boolean act(float delta) {
        GameState.battleSelectedCreature.setScale(1f);
        return true;
    }
}
