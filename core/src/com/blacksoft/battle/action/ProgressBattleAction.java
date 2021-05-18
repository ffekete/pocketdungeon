package com.blacksoft.battle.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.blacksoft.battle.BattlePhase;
import com.blacksoft.state.GameState;

public class ProgressBattleAction extends Action {
    @Override
    public boolean act(float delta) {

        GameState.nextBattleAction = null;
        GameState.nextAttackTarget = null;
        GameState.nextAttackTargetImage = null;
        GameState.battleSelectedCreature = null;
        GameState.battlePhase = BattlePhase.FinishTurn;
        return true;
    }
}
