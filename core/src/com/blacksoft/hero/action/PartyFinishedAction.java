package com.blacksoft.hero.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.blacksoft.dungeon.phase.GamePhase;
import com.blacksoft.hero.Party;
import com.blacksoft.screen.UIFactory;
import com.blacksoft.state.GameState;
import com.blacksoft.state.UIState;
import com.blacksoft.user.actions.UserAction;

public class PartyFinishedAction extends Action {

    private Party party;

    public PartyFinishedAction(Party party) {
        this.party = party;
    }

    @Override
    public boolean act(float delta) {

        if(this.party.heroes.isEmpty()) {

            GameState.uiStage.addActor(UIFactory.I.addMovingLabel("BUILD PHASE"));
            GameState.uiStage.addActor(UIFactory.I.addMovingLabelShadow("BUILD PHASE"));

            GameState.gamePhase = GamePhase.Build;
            GameState.userAction = UserAction.Clean;

            UIState.battleScreen.setVisible(false);

            GameState.paused = false;

            GameState.stage.getActors().removeValue(party, true);

            return true;
        }

        return false;
    }
}
