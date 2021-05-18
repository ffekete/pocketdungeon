package com.blacksoft.creature.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.blacksoft.battle.BattlePhase;
import com.blacksoft.battle.action.MoveBattleToFinishTurnAction;
import com.blacksoft.creature.Creature;
import com.blacksoft.creature.modifier.DefenceModifier;
import com.blacksoft.state.GameState;

public class DefendSelfAction extends Action {


    private Creature targetCreature;

    public DefendSelfAction(Creature targetCreature) {
        this.targetCreature = targetCreature;
    }

    @Override
    public boolean act(float delta) {

        GameState.battlePhase = BattlePhase.Act;

        SequenceAction defenseAnimationAction = new SequenceAction();
        defenseAnimationAction.setActor(GameState.battleImages.get(targetCreature));
        defenseAnimationAction.addAction(Actions.moveBy(0, -5, 0.1f));
        defenseAnimationAction.addAction(Actions.moveBy(0, 5, 0.1f));

        SequenceAction sequenceAction = new SequenceAction();
        sequenceAction.addAction(defenseAnimationAction);
        DefenceModifier defenceModifier = new DefenceModifier(0, GameState.battleSelectedCreature, 1);
        GameState.battleSelectedCreature.modifiers.add(defenceModifier);
        defenceModifier.start(sequenceAction);

        sequenceAction.addAction(Actions.delay(1f));
        sequenceAction.addAction(new MoveBattleToFinishTurnAction());
        GameState.uiStage.addAction(sequenceAction);

        return true;
    }
}
