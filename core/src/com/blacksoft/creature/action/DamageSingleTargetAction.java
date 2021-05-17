package com.blacksoft.creature.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.blacksoft.creature.Creature;
import com.blacksoft.screen.UIFactory;
import com.blacksoft.state.GameState;
import com.blacksoft.ui.AnimatedImage;

public class DamageSingleTargetAction extends Action {

    private int damage;
    private Creature targetCreature;
    private AnimatedImage nextAttackTarget;

    public DamageSingleTargetAction(int damage) {
        this.damage = damage;
    }

    @Override
    public boolean act(float delta) {

        if(GameState.nextAttackTarget != null) {

            this.targetCreature = GameState.nextAttackTarget;
            this.nextAttackTarget = GameState.nextAttackTargetImage;

            UIFactory.I.createFloatingLabel(damage, (int)nextAttackTarget.getX() + 90 + 16, (int)nextAttackTarget.getY() + 60 + 32);

            SequenceAction shakeAction = new SequenceAction();
            shakeAction.addAction(Actions.moveBy(2, 0, 0.05f));
            shakeAction.addAction(Actions.moveBy(-4, 0, 0.05f));
            shakeAction.addAction(Actions.moveBy(4, 0, 0.05f));
            shakeAction.addAction(Actions.moveBy(-4, 0, 0.05f));
            shakeAction.addAction(Actions.moveBy(2, 0, 0.05f));
            shakeAction.addAction(new ReduceHpAction(this.targetCreature, damage));

            this.nextAttackTarget.addAction(shakeAction);

            GameState.nextBattleAction = null;
            GameState.nextAttackTarget = null;
            GameState.nextAttackTargetImage = null;

            return true;
        }

        return false;
    }
}
