package com.blacksoft.battle.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.blacksoft.creature.Direction;
import com.blacksoft.creature.State;
import com.blacksoft.state.GameState;

public class ResetPartyStatusAction extends Action {
    @Override
    public boolean act(float delta) {
        GameState.party.direction = Direction.Left;
        GameState.party.state = State.Idle;
        GameState.party.heroes.forEach(hero -> {
            hero.direction = Direction.Left;
            hero.setState(State.Idle);
        });
        return true;
    }
}
