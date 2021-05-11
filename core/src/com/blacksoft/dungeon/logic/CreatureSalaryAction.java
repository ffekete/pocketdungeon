package com.blacksoft.dungeon.logic;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.blacksoft.screen.UIFactory;
import com.blacksoft.state.GameState;

import java.awt.*;

import static com.blacksoft.state.Config.NO_SALARY_MORALE_PENALTY;

public class CreatureSalaryAction extends Action {

    private float duration = 0f;

    @Override
    public boolean act(float delta) {

        duration += delta;
        if (duration >= 12) {

            GameState.creatures.forEach(creature -> {

                if(creature.getSalaryRequest() > 0) {
                    if (GameState.gold - creature.getSalaryRequest() >= 0) {
                        GameState.gold -= creature.getSalaryRequest();

                        Label label = UIFactory.I.createFloatingLabel(creature.getSalaryRequest(), (int) creature.getX() / 16 * 16, (int) creature.getY() / 16 * 16);
                        label.setColor(Color.valueOf("FFD700"));

                        GameState.uiStage.addActor(label);

                    } else {
                        Label label = UIFactory.I.createFloatingLabel(((int)-NO_SALARY_MORALE_PENALTY), (int) creature.getX() / 16 * 16, (int) creature.getY() / 16 * 16);
                        label.setColor(Color.RED);
                        creature.reduceMorale(NO_SALARY_MORALE_PENALTY);
                        GameState.uiStage.addActor(label);
                    }
                }
            });

            duration = 0;
        }

        return false;
    }
}
