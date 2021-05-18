package com.blacksoft.creature.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.blacksoft.battle.BattlePhase;
import com.blacksoft.battle.action.MoveBattleToFinishTurnAction;
import com.blacksoft.creature.modifier.PoisonModifier;
import com.blacksoft.hero.Hero;
import com.blacksoft.state.GameState;

public class PoisonTargetAction extends Action {

    @Override
    public boolean act(float delta) {

        GameState.battlePhase = BattlePhase.Act;

        if (GameState.nextAttackTarget != null) {
            SequenceAction attackAnimationAction = new SequenceAction();

            attackAnimationAction.setActor(GameState.battleImages.get(GameState.battleSelectedCreature));
            if (Hero.class.isAssignableFrom(GameState.battleSelectedCreature.getClass())) {
                attackAnimationAction.addAction(Actions.moveBy(-10, 0, 0.1f));
                attackAnimationAction.addAction(Actions.moveBy(10, 0, 0.1f));
            } else {
                attackAnimationAction.addAction(Actions.moveBy(10, 0, 0.1f));
                attackAnimationAction.addAction(Actions.moveBy(-10, 0, 0.1f));
            }

            SequenceAction sequenceAction = new SequenceAction();
            sequenceAction.addAction(attackAnimationAction);
            PoisonModifier poisonModifier = new PoisonModifier(5, GameState.nextAttackTarget, 1);
            GameState.nextAttackTarget.modifiers.add(poisonModifier);
            poisonModifier.start(sequenceAction);
            sequenceAction.addAction(Actions.delay(1f));
            sequenceAction.addAction(new MoveBattleToFinishTurnAction());
            GameState.uiStage.addAction(sequenceAction);
            return true;
        }

        return false;
    }
}
