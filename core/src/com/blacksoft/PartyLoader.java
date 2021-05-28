package com.blacksoft;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.blacksoft.battle.action.BattleInitiationAction;
import com.blacksoft.creature.Skeleton;
import com.blacksoft.hero.Party;
import com.blacksoft.hero.Rogue;
import com.blacksoft.hero.Wizard;
import com.blacksoft.screen.action.AddActorAction;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;

public class PartyLoader {

    public static void load(SequenceAction initAction) {
        // Create new party
        Party party = new Party();
        GameState.party = party;

        party.heroes.add(new Wizard(party));
        party.heroes.add(new Rogue(party));

        party.setX(Config.DUNGEON_ENTRANCE_LOCATION.x * 16);
        party.setY((Config.DUNGEON_ENTRANCE_LOCATION.y + 2)* 16);

        AddActorAction addPartyToStageAction = new AddActorAction(party, GameState.stage);

        // send them exploring
        initAction.addAction(Actions.delay(0.5f));
        initAction.addAction(addPartyToStageAction);
        initAction.setActor(party);

        party.addAction(new BattleInitiationAction(party));
    }

}
