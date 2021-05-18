package com.blacksoft.hero.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.blacksoft.creature.Creature;
import com.blacksoft.state.GameState;

public class RemoveFromPartyAction extends Action {

    private Creature target;

    public RemoveFromPartyAction(Creature target) {
        this.target = target;
    }

    @Override
    public boolean act(float delta) {

        GameState.party.heroes.remove(target);

        return true;
    }
}
