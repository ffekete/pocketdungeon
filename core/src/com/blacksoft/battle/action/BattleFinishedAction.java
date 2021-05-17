package com.blacksoft.battle.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.blacksoft.creature.Creature;
import com.blacksoft.dungeon.phase.GamePhase;
import com.blacksoft.hero.Party;
import com.blacksoft.screen.UIFactory;
import com.blacksoft.state.GameState;
import com.blacksoft.state.UIState;
import com.blacksoft.user.actions.UserAction;

import java.util.List;

public class BattleFinishedAction extends Action {

    private Party party;
    private List<Creature> creatures;

    public BattleFinishedAction(Party party,
                                List<Creature> creatures) {
        this.party = party;
        this.creatures = creatures;
    }

    @Override
    public boolean act(float delta) {

        if(creatures.stream().noneMatch(creature -> creature.hp > 0)) {
            UIState.battleScreen.setVisible(false);
            GameState.paused = false;
            return true;
        }

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
