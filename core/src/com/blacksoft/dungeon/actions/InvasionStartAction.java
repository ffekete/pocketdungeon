package com.blacksoft.dungeon.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.blacksoft.dungeon.phase.GamePhase;
import com.blacksoft.hero.Party;
import com.blacksoft.hero.Rogue;
import com.blacksoft.hero.Wizard;
import com.blacksoft.battle.action.BattleInitiationAction;
import com.blacksoft.hero.action.ExploringAction;
import com.blacksoft.battle.action.BattleFinishedAction;
import com.blacksoft.screen.UIFactory;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;

public class InvasionStartAction extends Action {

    @Override
    public boolean act(float delta) {

        // let the fun begin
        GameState.gamePhase = GamePhase.Invasion;

        Label intrudersLabel = UIFactory.I.addMovingLabel("INVASION");
        Label intrudersShadowLabel = UIFactory.I.addMovingLabelShadow("INVASION");
        GameState.uiStage.addActor(intrudersLabel);
        GameState.uiStage.addActor(intrudersShadowLabel);
        intrudersShadowLabel.toFront();
        intrudersLabel.toFront();

        // Create new party
        Party party = new Party();
        GameState.party = party;

        Wizard wizard = new Wizard(party);
        party.heroes.add(wizard);
        party.heroes.add(new Rogue(party));

        party.setPosition(Config.DUNGEON_ENTRANCE_LOCATION.x * 16, Config.DUNGEON_ENTRANCE_LOCATION.y * 16);

        party.setX(Config.DUNGEON_ENTRANCE_LOCATION.x * 16);
        party.setY(Config.DUNGEON_ENTRANCE_LOCATION.y * 16);

        GameState.stage.addActor(party);

        SequenceAction sequenceAction = new SequenceAction();

        // send them exploring
        sequenceAction.addAction(Actions.delay(5));
        sequenceAction.addAction(new ExploringAction(party));
        party.addAction(sequenceAction);
        party.addAction(new BattleInitiationAction(party));

        return true;
    }
}
