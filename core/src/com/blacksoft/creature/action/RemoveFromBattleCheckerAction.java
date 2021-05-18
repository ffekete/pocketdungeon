package com.blacksoft.creature.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.blacksoft.creature.Creature;
import com.blacksoft.state.GameState;
import com.blacksoft.ui.AnimatedImage;

public class RemoveFromBattleCheckerAction extends Action {

    private Creature targetCreature;
    private AnimatedImage targetCreatureBattleImage;

    public RemoveFromBattleCheckerAction(Creature targetCreature,
                                         AnimatedImage targetCreatureBattleImage) {
        this.targetCreature = targetCreature;
        this.targetCreatureBattleImage = targetCreatureBattleImage;
    }

    @Override
    public boolean act(float delta) {

        if (targetCreature.hp <= 0) {
            SequenceAction sequenceAction = new SequenceAction();
            sequenceAction.addAction(Actions.fadeOut(0.5f));
            sequenceAction.addAction(Actions.removeActor());
            targetCreatureBattleImage.addAction(sequenceAction);

            GameState.battleHpAndMpProgressBars.get(targetCreature).forEach(image -> {
                SequenceAction removeSkillImagesAction = new SequenceAction();
                removeSkillImagesAction.addAction(Actions.fadeOut(0.1f));
                removeSkillImagesAction.addAction(Actions.removeActor());
                image.addAction(removeSkillImagesAction);
            });

            GameState.battleSkillIcons.get(targetCreature).forEach(image -> {
                SequenceAction removeSkillImagesAction = new SequenceAction();
                removeSkillImagesAction.addAction(Actions.fadeOut(0.1f));
                removeSkillImagesAction.addAction(Actions.removeActor());
                image.addAction(removeSkillImagesAction);
            });
        }

        return true;
    }
}
