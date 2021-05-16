package com.blacksoft.hero.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.blacksoft.creature.util.PartyuActionsUtil;
import com.blacksoft.hero.Party;
import com.blacksoft.state.GameState;

public class ResetPartyActionsAction extends Action {

    private Party party;

    public ResetPartyActionsAction(Party party) {
        this.party = party;
    }

    @Override
    public boolean act(float delta) {

        if(GameState.paused) {
            return false;
        }

        PartyuActionsUtil.resetPartyActions(party);
        return true;
    }
}
