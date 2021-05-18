package com.blacksoft.creature.action;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.blacksoft.battle.BattlePhase;
import com.blacksoft.battle.action.ClearSelectedCreatureAction;
import com.blacksoft.battle.action.ProgressBattleAction;
import com.blacksoft.creature.Creature;
import com.blacksoft.hero.Hero;
import com.blacksoft.screen.UIFactory;
import com.blacksoft.state.GameState;
import com.blacksoft.state.UIState;
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

        if (GameState.nextAttackTarget != null) {

            GameState.battlePhase = BattlePhase.Act;

            this.targetCreature = GameState.nextAttackTarget;
            this.nextAttackTarget = GameState.nextAttackTargetImage;

            int calculatedDamage = Math.max(1, damage - this.targetCreature.getMeleeDefence());

            UIFactory.I.createFloatingLabel(calculatedDamage, (int) nextAttackTarget.getX() + 90 + 16, (int) nextAttackTarget.getY() + 60 + 32);

            AnimatedImage animatedImage = new AnimatedImage(
                    new Animation<TextureRegion>(0.025f, TextureRegion.split(UIState.meleeAttackAnimationsTexture.getTexture(), 16, 16)[0]), false);

            animatedImage.setPosition(this.nextAttackTarget.getX() + 70, this.nextAttackTarget.getY() + 60);
            animatedImage.setScale(4f);

            SequenceAction playAnimationAction = new SequenceAction();
            playAnimationAction.addAction(Actions.delay(0.2f));
            playAnimationAction.addAction(Actions.removeActor());
            animatedImage.addAction(playAnimationAction);
            GameState.uiStage.addActor(animatedImage);

            SequenceAction attackAnimationAction = new SequenceAction();
            attackAnimationAction.setActor(GameState.battleImages.get(GameState.battleSelectedCreature));
            if(Hero.class.isAssignableFrom(GameState.battleSelectedCreature.getClass())) {
                attackAnimationAction.addAction(Actions.moveBy(-10, 0, 0.1f));
                attackAnimationAction.addAction(Actions.moveBy(10, 0, 0.1f));
            } else {
                attackAnimationAction.addAction(Actions.moveBy(10, 0, 0.1f));
                attackAnimationAction.addAction(Actions.moveBy(-10, 0, 0.1f));
            }
            GameState.battleImages.get(GameState.battleSelectedCreature).addAction(attackAnimationAction);

            SequenceAction shakeAction = new SequenceAction();
            shakeAction.addAction(Actions.delay(0.1f));
            shakeAction.addAction(Actions.moveBy(4, 2, 0.05f));
            shakeAction.addAction(Actions.moveBy(-8, -4, 0.05f));
            shakeAction.addAction(Actions.moveBy(8, 4, 0.05f));
            shakeAction.addAction(Actions.moveBy(-8, -4, 0.05f));
            shakeAction.addAction(Actions.moveBy(4, 2, 0.05f));

            shakeAction.addAction(new ReduceHpAction(this.targetCreature, calculatedDamage));
            shakeAction.addAction(new ClearSelectedCreatureAction());
            shakeAction.addAction(Actions.delay(1f));
            shakeAction.addAction(new ProgressBattleAction());
            shakeAction.addAction(new RemoveFromBattleCheckerAction(targetCreature, nextAttackTarget));

            this.nextAttackTarget.addAction(shakeAction);

            return true;
        }

        return false;
    }
}
