package com.blacksoft.creature.modifier;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.blacksoft.creature.Creature;
import com.blacksoft.screen.UIFactory;
import com.blacksoft.skill.MeleeDefense;
import com.blacksoft.state.GameState;
import com.blacksoft.ui.AnimatedImage;

import static com.blacksoft.state.Config.BATTLE_SCREEN_POS_X;
import static com.blacksoft.state.Config.BATTLE_SCREEN_POS_Y;

public class DefenceModifier implements Modifier{

    private int duration;
    private Creature target;
    private int amount;

    public DefenceModifier(int duration,
                           Creature target,
                           int amount) {
        this.duration = duration;
        this.target = target;
        this.amount = amount;
    }

    @Override
    public void apply(SequenceAction sequenceAction) {
        if(duration > 0) {
            duration--;
            target.tempDefenceModifier += amount;
            AnimatedImage image = GameState.battleImages.get(target);
            Label label = UIFactory.I.createFloatingLabelWithIcon(amount, new TextureRegion(MeleeDefense.icon), (int)image.getX() + BATTLE_SCREEN_POS_X + 24, (int)image.getY() + BATTLE_SCREEN_POS_Y + 48, sequenceAction);
            sequenceAction.addAction(Actions.delay(0.5f));
        }
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public int getAmount() {
        return this.amount;
    }

    @Override
    public void finish(SequenceAction sequenceAction) {

    }

    @Override
    public void start(SequenceAction sequenceAction) {
        target.tempDefenceModifier += amount;
        AnimatedImage image = GameState.battleImages.get(target);
        UIFactory.I.createFloatingLabelWithIcon(amount, new TextureRegion(MeleeDefense.icon), (int)image.getX() + BATTLE_SCREEN_POS_X + 24, (int)image.getY() + BATTLE_SCREEN_POS_Y + 48, sequenceAction);
        sequenceAction.addAction(Actions.delay(0.5f));
    }

    @Override
    public Modifier merge(Modifier modifier) {
        if(modifier.getClass().isAssignableFrom(this.getClass())) {
            this.amount += modifier.getAmount();
            return null;
        }

        return modifier;
    }
}
