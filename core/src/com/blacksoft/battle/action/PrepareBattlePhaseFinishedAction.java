package com.blacksoft.battle.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.blacksoft.battle.BattlePhase;
import com.blacksoft.state.GameState;

public class PrepareBattlePhaseFinishedAction extends Action {
    @Override
    public boolean act(float delta) {
        GameState.battlePhase = BattlePhase.Prepare;
        return true;
    }
}
