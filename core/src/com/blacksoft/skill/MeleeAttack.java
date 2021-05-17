package com.blacksoft.skill;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.blacksoft.creature.Creature;
import com.blacksoft.creature.action.DamageSingleTargetAction;
import com.blacksoft.state.GameState;

import java.util.List;
import java.util.Optional;

public class MeleeAttack implements Skill {

    private static final Texture icon;

    static {
        icon = new Texture(Gdx.files.internal("skill/AttackIcon.png"));
    }

    @Override
    public void act(List<Creature> creatures,
                    List<Creature> heroes) {
        Optional<Creature> target = creatures.stream().filter(creature -> creature.hp > 0).findFirst();

        target.ifPresent(creature -> {
            GameState.nextAttackTarget = creature;
            GameState.nextAttackTargetImage = GameState.battleImages.get(creature);

            SequenceAction attackAnimationAction = new SequenceAction();
            attackAnimationAction.setActor(GameState.battleImages.get(GameState.battleSelectedCreature));
            attackAnimationAction.addAction(Actions.moveBy(-10, 0, 0.1f));
            attackAnimationAction.addAction(Actions.moveBy(10, 0, 0.1f));

            SequenceAction sequenceAction = new SequenceAction();
            sequenceAction.addAction(Actions.delay(2f));
            sequenceAction.addAction(attackAnimationAction);
            sequenceAction.addAction(new DamageSingleTargetAction(GameState.battleSelectedCreature.getMeleeDamage()));
            sequenceAction.addAction(Actions.delay(2f));
            GameState.uiStage.addAction(sequenceAction);
        });
    }

    @Override
    public Texture getIcon() {
        return icon;
    }
}
