package com.blacksoft.creature.action;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.blacksoft.creature.Creature;
import com.blacksoft.screen.UIFactory;
import com.blacksoft.state.GameState;

public class RestAction extends Action {

    private Creature creature;

    private float duration = 0f;

    public RestAction(Creature creature) {
        this.creature = creature;
    }

    @Override
    public boolean act(float delta) {

        if(GameState.paused) {
            return false;
        }

        duration += delta;

        if (duration >= 5f) {
            creature.hp = creature.getMaxHp();
            Label label = UIFactory.I.createFloatingLabel(100, (int) creature.getX(), (int) creature.getY());
            label.setColor(Color.GREEN);
            return true;
        }
        return false;
    }
}
