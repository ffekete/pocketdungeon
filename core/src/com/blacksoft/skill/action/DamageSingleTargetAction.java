package com.blacksoft.skill.action;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
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

            SequenceAction battleSequenceAction = new SequenceAction();
            battleSequenceAction.addAction(Actions.delay(0.1f));
            battleSequenceAction.addAction(Actions.moveBy(4, 2, 0.05f));
            battleSequenceAction.addAction(Actions.moveBy(-8, -4, 0.05f));
            battleSequenceAction.addAction(Actions.moveBy(8, 4, 0.05f));
            battleSequenceAction.addAction(Actions.moveBy(-8, -4, 0.05f));
            battleSequenceAction.addAction(Actions.moveBy(4, 2, 0.05f));

            battleSequenceAction.addAction(new ReduceHpAction(this.targetCreature, calculatedDamage));
            battleSequenceAction.addAction(new ClearSelectedCreatureModificationsAction());
            battleSequenceAction.addAction(Actions.delay(0.5f));
            battleSequenceAction.addAction(new MoveBattleToFinishTurnAction());
            battleSequenceAction.addAction(new RemoveFromBattleCheckerAction(targetCreature));
            battleSequenceAction.addAction(new RemoveTargetSelectionAction());
            battleSequenceAction.addAction(Actions.delay(0.5f));
            battleSequenceAction.addAction(new SetUserAction(UserAction.Idle));
            battleSequenceAction.addAction(new BattleFinishedCheckingAction());

            this.nextAttackTarget.addAction(battleSequenceAction);

            return true;
        }

        return false;
    }
}
