package com.blacksoft.creature.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.blacksoft.creature.Creature;
import com.blacksoft.ui.AnimatedImage;

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
        }

        return false;
    }
}
