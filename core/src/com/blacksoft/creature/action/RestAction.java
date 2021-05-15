package com.blacksoft.creature.action;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.actions.RemoveActorAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.actions.VisibleAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.blacksoft.creature.Creature;
import com.blacksoft.screen.UIFactory;
import com.blacksoft.screen.action.AddActorAction;
import com.blacksoft.state.GameState;

public class RestAction extends Action {

    private Creature creature;

    private float duration = 0f;

    public RestAction(Creature creature) {
        this.creature = creature;
    }

    @Override
    public boolean act(float delta) {

        if(GameState.paused) {
            return false;
        }

        duration += delta;

        // todo rest when mp is low

        if (duration >= 5f) {
            int oldHpValue = creature.hp;
            int oldMpValue = creature.mp;
            creature.hp = creature.getMaxHp();
            creature.mp = creature.getMaxHp();
            SequenceAction sequenceAction = new SequenceAction();
            Label hpLabel = UIFactory.I.createFloatingLabel(creature.getMaxHp() - oldHpValue, (int) creature.getX(), (int) creature.getY(), sequenceAction, 0f);
            hpLabel.setColor(Color.RED);

            RemoveActorAction removeHpLabelAction = new RemoveActorAction();
            removeHpLabelAction.setActor(hpLabel);
            sequenceAction.addAction(removeHpLabelAction);

            if(creature.getMaxMp() - oldMpValue  > 0) {
                Label mpLabel = UIFactory.I.createFloatingLabel(creature.getMaxMp() - oldMpValue, (int) creature.getX(), (int) creature.getY(), sequenceAction, 0.2f);

                RemoveActorAction removeMpLabelAction = new RemoveActorAction();
                removeMpLabelAction.setActor(mpLabel);

                sequenceAction.addAction(removeMpLabelAction);
                mpLabel.setColor(Color.BLUE);
            }

            GameState.uiStage.addAction(sequenceAction);

            return true;
        }
        return false;
    }
}
