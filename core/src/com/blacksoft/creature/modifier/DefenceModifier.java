package com.blacksoft.creature.modifier;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.blacksoft.creature.Creature;
import com.blacksoft.screen.UIFactory;
import com.blacksoft.skill.MeleeDefense;
import com.blacksoft.state.GameState;
import com.blacksoft.state.UIState;
import com.blacksoft.ui.AnimatedImage;

import java.awt.*;

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

        start();
    }

    @Override
    public void apply() {
        if(duration > 0) {
            duration--;
            target.tempDefenceModifier += amount;
            AnimatedImage image = GameState.battleImages.get(target);
            Label label = UIFactory.I.createFloatingLabel(amount, (int)image.getX() + 90 + 24, (int)image.getY() + 60 + 48);
            label.setColor(Color.LIME);
        }
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public void finish() {

    }

    @Override
    public void start() {
        target.tempDefenceModifier += amount;
        AnimatedImage image = GameState.battleImages.get(target);
        Label label = UIFactory.I.createFloatingLabelWithIcon(amount, new TextureRegion(MeleeDefense.icon), (int)image.getX() + 90 + 24, (int)image.getY() + 60 + 48);
        label.setColor(Color.LIME);
    }
}
