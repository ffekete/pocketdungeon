package com.blacksoft.battle.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.blacksoft.state.GameState;

public class ClearBattleSelectedCreatureAction extends Action {
    @Override
    public boolean act(float delta) {
        GameState.battleSelectedCreature = null;
        return true;
    }
}
