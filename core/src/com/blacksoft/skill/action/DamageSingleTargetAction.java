package com.blacksoft.skill.action;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.blacksoft.battle.BattlePhase;
import com.blacksoft.battle.action.BattleFinishedCheckingAction;
import com.blacksoft.battle.action.ClearSelectedCreatureModificationsAction;
import com.blacksoft.battle.action.MoveBattleToFinishTurnAction;
import com.blacksoft.battle.action.RemoveTargetSelectionAction;
import com.blacksoft.creature.Creature;
import com.blacksoft.creature.action.ReduceHpAction;
import com.blacksoft.creature.action.RemoveFromBattleCheckerAction;
import com.blacksoft.hero.Hero;
import com.blacksoft.screen.UIFactory;
import com.blacksoft.state.GameState;
import com.blacksoft.state.UIState;
import com.blacksoft.ui.AnimatedImage;
import com.blacksoft.user.actions.SetUserAction;
import com.blacksoft.user.actions.UserAction;

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
                    new Animation<>(0.025f, TextureRegion.split(UIState.meleeAttackAnimationsTexture.getTexture(), 16, 16)[0]), false);

            animatedImage.setPosition(this.nextAttackTarget.getX() + 70, this.nextAttackTarget.getY() + 60);
            animatedImage.setScale(4f);

            SequenceAction playAttackEffectAnimationAction = new SequenceAction();
            playAttackEffectAnimationAction.addAction(Actions.delay(0.2f));
            playAttackEffectAnimationAction.addAction(Actions.removeActor());
            animatedImage.addAction(playAttackEffectAnimationAction);
            GameState.uiStage.addActor(animatedImage);


            System.out.println("Selected creature is attacking: " + GameState.battleSelectedCreature);
            Image creatureImage = GameState.battleImages.get(GameState.battleSelectedCreature);

            SequenceAction attackAnimationAction = new SequenceAction();
            attackAnimationAction.setActor(creatureImage);
            if(Hero.class.isAssignableFrom(GameState.battleSelectedCreature.getClass())) {
                attackAnimationAction.addAction(Actions.moveBy(-10, 0, 0.1f));
                attackAnimationAction.addAction(Actions.moveBy(10, 0, 0.1f));
            } else {
                attackAnimationAction.addAction(Actions.moveBy(10, 0, 0.1f));
                attackAnimationAction.addAction(Actions.moveBy(-10, 0, 0.1f));
            }
            creatureImage.addAction(attackAnimationAction);

            SequenceAction creatureSequenceAction = new SequenceAction();
            SequenceAction genericBattleActionSequence = new SequenceAction();

            creatureSequenceAction.addAction(Actions.delay(0.1f));
            creatureSequenceAction.addAction(Actions.moveBy(4, 2, 0.05f));
            creatureSequenceAction.addAction(Actions.moveBy(-8, -4, 0.05f));
            creatureSequenceAction.addAction(Actions.moveBy(8, 4, 0.05f));
            creatureSequenceAction.addAction(Actions.moveBy(-8, -4, 0.05f));
            creatureSequenceAction.addAction(Actions.moveBy(4, 2, 0.05f));

            creatureSequenceAction.addAction(new ReduceHpAction(this.targetCreature, calculatedDamage));
            creatureSequenceAction.addAction(new ClearSelectedCreatureModificationsAction());
            creatureSequenceAction.addAction(Actions.delay(0.5f));
            creatureSequenceAction.addAction(new RemoveFromBattleCheckerAction(targetCreature));
            creatureSequenceAction.addAction(new RemoveTargetSelectionAction());
            creatureSequenceAction.setActor(GameState.nextAttackTargetImage);

            genericBattleActionSequence.addAction(Actions.delay(0.5f));
            genericBattleActionSequence.addAction(new SetUserAction(UserAction.Idle));
            genericBattleActionSequence.addAction(new BattleFinishedCheckingAction());
            genericBattleActionSequence.addAction(new MoveBattleToFinishTurnAction());

            SequenceAction sequenceAction = new SequenceAction();
            sequenceAction.addAction(creatureSequenceAction);
            sequenceAction.addAction(genericBattleActionSequence);

            GameState.stage.addAction(sequenceAction);

            return true;
        }

        return false;
    }
}
