package com.blacksoft.creature.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.blacksoft.creature.Creature;
import com.blacksoft.state.GameState;
import com.blacksoft.ui.AnimatedImage;

import java.awt.*;

public class RemoveFromBattleCheckerAction extends Action {

    private Creature targetCreature;
    private AnimatedImage targetCreatureBattleImage;

    public RemoveFromBattleCheckerAction(Creature targetCreature, AnimatedImage targetCreatureBattleImage) {
        this.targetCreature = targetCreature;
        this.targetCreatureBattleImage = targetCreatureBattleImage;
    }

    @Override
    public boolean act(float delta) {

        if(targetCreature.hp <= 0) {
            targetCreatureBattleImage.remove();
            GameState.battleSkillIcons.get(targetCreature).forEach(image -> {
                    image.remove();
            });
        }

        return false;
    }
}
