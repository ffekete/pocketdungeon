package com.blacksoft.battle.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.blacksoft.battle.BattlePhase;
import com.blacksoft.dungeon.actions.ui.CleanIndicatorUpdater;
import com.blacksoft.dungeon.phase.GamePhase;
import com.blacksoft.screen.UIFactory;
import com.blacksoft.state.GameState;
import com.blacksoft.state.UIState;
import com.blacksoft.user.actions.UserAction;

public class BattleFinishedCheckingAction extends Action {

    @Override
    public boolean act(float delta) {

        if(GameState.creaturesInvolvedInBattle.stream().noneMatch(creature -> creature.hp > 0)) {
            UIState.battleScreen.remove();
            GameState.paused = false;
            UIState.battleSelectionCursor.setVisible(false);
            GameState.battleSkillIcons.clear();
            GameState.battleImages.clear();
            GameState.battleSelectedCreature = null;
            GameState.nextBattleAction = null;
            GameState.isCombatSequence = false;

            GameState.nextAttackTarget = null;
            GameState.nextAttackTargetImage = null;

            GameState.battlePhase = BattlePhase.FinishTurn; // just for sure

            return true;
        }

        if(GameState.party.heroes.isEmpty()) {

            GameState.battleSkillIcons.clear();
            GameState.battleImages.clear();
            GameState.battleSelectedCreature = null;
            GameState.nextBattleAction = null;

            GameState.uiStage.addActor(UIFactory.I.addMovingLabelShadow("BUILD PHASE"));
            GameState.uiStage.addActor(UIFactory.I.addMovingLabel("BUILD PHASE"));

            GameState.gamePhase = GamePhase.Build;
            GameState.userAction = UserAction.Clean;
            CleanIndicatorUpdater.update(GameState.dungeon);

            UIState.battleScreen.remove();
            UIState.battleSelectionCursor.setVisible(false);

            GameState.paused = false;

            GameState.stage.getActors().removeValue(GameState.party, true);

            GameState.isCombatSequence = false;

            GameState.nextAttackTarget = null;
            GameState.nextAttackTargetImage = null;

            GameState.battlePhase = BattlePhase.FinishTurn; // just for sure

            return true;
        }

        return true;
    }
}
