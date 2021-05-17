package com.blacksoft.creature.util;

import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.math.Vector2;
import com.blacksoft.hero.action.MoveAndExplorePartyToTileAction;
import com.blacksoft.dungeon.Node;
import com.blacksoft.hero.Party;
import com.blacksoft.hero.action.ResetPartyActionsAction;
import com.blacksoft.state.GameState;

public class PartyActionsUtil {

    public static void moveToAndExploreTargetNode(Party party,
                                                  Vector2 target) {
        GraphPath<Node> path = GameState.cityGraph.findPath(GameState.dungeon.getNode(party.getX() / 16, party.getY() / 16),
                GameState.dungeon.getNode(target.x, target.y));

        resetPartyActions(party);
        party.finishedAllActions = false;
        for (int i = 0; i < path.getCount(); i++) {
            Node node = path.get(i);
            MoveAndExplorePartyToTileAction moveToTileAction = new MoveAndExplorePartyToTileAction(party, new Vector2(node.x * 16, node.y * 16));
            moveToTileAction.setActor(party);
            party.sequenceActions.addAction(moveToTileAction);
        }
        party.sequenceActions.addAction(new ResetPartyActionsAction(party));
    }

    public static void resetPartyActions(Party party) {
        party.sequenceActions.reset();
        party.addAction(party.sequenceActions);
        party.finishedAllActions = true;
    }
}
