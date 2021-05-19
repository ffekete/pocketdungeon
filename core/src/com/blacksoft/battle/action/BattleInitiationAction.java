package com.blacksoft.battle.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.blacksoft.battle.BattleInitializer;
import com.blacksoft.battle.BattlePhase;
import com.blacksoft.creature.Creature;
import com.blacksoft.hero.Party;
import com.blacksoft.state.GameState;

import java.util.List;
import java.util.stream.Collectors;

public class BattleInitiationAction extends Action {

    private float duration = 0f;
    private Party party;

    public BattleInitiationAction(Party party) {
        this.party = party;
    }

    @Override
    public boolean act(float delta) {

        if (GameState.paused) {
            return false;
        }

        duration += delta;

        if (duration >= 0.05f) {

            List<Creature> creatures = GameState.creatures.stream()
                    .filter(creature -> {
                        return (creature.getX() / 16 == party.getX() / 16 && creature.getY() / 16 == party.getY() / 16) ||
                                (creature.getX() / 16 == party.getX() / 16 - 1 && creature.getY() / 16 == party.getY() / 16) ||
                                (creature.getX() / 16 == party.getX() / 16 + 1 && creature.getY() / 16 == party.getY() / 16) ||
                                (creature.getX() / 16 == party.getX() / 16 && creature.getY() / 16 == party.getY() / 16 - 1) ||
                                (creature.getX() / 16 == party.getX() / 16 && creature.getY() / 16 == party.getY() / 16 + 1);

                    })
                    .collect(Collectors.toList());

            if (!creatures.isEmpty()) {
                GameState.paused = true;
                GameState.creaturesInvolvedInBattle = creatures;
                BattleInitializer.init(creatures, party);
            }

            duration = 0;
        }

        return false;
    }
}
