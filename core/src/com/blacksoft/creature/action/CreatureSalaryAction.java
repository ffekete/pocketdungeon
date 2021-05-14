package com.blacksoft.creature.action;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.blacksoft.creature.Creature;
import com.blacksoft.dungeon.Tile;
import com.blacksoft.screen.UIFactory;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;
import com.blacksoft.state.UIState;

import static com.blacksoft.state.Config.MAP_HEIGHT;
import static com.blacksoft.state.Config.MAP_WIDTH;
import static com.blacksoft.state.Config.NO_SALARY_MORALE_PENALTY;

public class CreatureSalaryAction extends Action {

    private float duration = 0f;

    @Override
    public boolean act(float delta) {

        if(GameState.paused) {
            return false;
        }

        int old = GameState.gold;

        duration += delta;
        if (duration >= 12) {

            int amount = 0;

            for (int i = 0; i < MAP_WIDTH; i++) {
                for (int j = 0; j < MAP_HEIGHT; j++) {
                    if (GameState.dungeon.nodes[i][j].tile == Tile.Treasury) {
                        amount += GameState.dungeon.nodes[i][j].building.getUpgradeLevel() * Config.TREASURY_INCOME_VALUE;
                    }
                }
            }

            GameState.gold += amount;

            for(Creature creature : GameState.creatures) {

                if (creature.getSalaryRequest() > 0) {
                    if (GameState.gold - creature.getSalaryRequest() >= 0) {
                        GameState.gold -= creature.getSalaryRequest();

                        Label label = UIFactory.I.createFloatingLabelWithIcon(creature.getSalaryRequest(), UIState.GoldIconImage, (int) creature.getX() / 16 * 16, (int) creature.getY() / 16 * 16);
                        label.setColor(Color.valueOf("FFD700"));

                    } else {
                        Label label = UIFactory.I.createFloatingLabelWithIcon(((int) -NO_SALARY_MORALE_PENALTY), UIState.MoraleIconImage, (int) creature.getX() / 16 * 16, (int) creature.getY() / 16 * 16);
                        label.setColor(Color.RED);
                        creature.reduceMorale(NO_SALARY_MORALE_PENALTY);
                    }
                }
            }

            if (GameState.gold > GameState.maxGoldCapacity) {
                GameState.gold = GameState.maxGoldCapacity;
            }

            if (old != GameState.gold) {
                UIFactory.I.updateLabelAmount(old, GameState.gold, UIState.goldLabel, "%s/%s", GameState.maxGoldCapacity);
            }

            duration = 0;
        }

        return false;
    }
}
