package com.blacksoft.dungeon.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.blacksoft.dungeon.phase.GamePhase;
import com.blacksoft.hero.Party;
import com.blacksoft.hero.Wizard;
import com.blacksoft.hero.action.ExploringAction;
import com.blacksoft.screen.UIFactory;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;

public class InvasionStartAction extends Action {

    @Override
    public boolean act(float delta) {

        // let the fun begin
        GameState.gamePhase = GamePhase.Invasion;

        GameState.uiStage.addActor(UIFactory.I.addMovingLabel("INTRUDERS"));
        GameState.uiStage.addActor(UIFactory.I.addMovingLabelShadow("INTRUDERS"));

        // Create new party
        Party party = new Party();

        Wizard wizard = new Wizard();
        party.heroes.add(wizard);

        party.setPosition(Config.DUNGEON_ENTRANCE_LOCATION.x * 16, Config.DUNGEON_ENTRANCE_LOCATION.y * 16);

        party.setX(Config.DUNGEON_ENTRANCE_LOCATION.x * 16);
        party.setY(Config.DUNGEON_ENTRANCE_LOCATION.y * 16);

        GameState.stage.addActor(party);

        // send them exploring
        party.addAction(new ExploringAction(party));

        return true;
    }
}
