package com.blacksoft.hero.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.blacksoft.creature.action.TileFinder;
import com.blacksoft.dungeon.Node;
import com.blacksoft.dungeon.Tile;
import com.blacksoft.hero.Party;

import static com.blacksoft.creature.util.PartyuActionsUtil.moveToAndExploreTargetNode;

public class ExploringAction extends Action {


    private final Party party;
    private Vector2 targetNode;

    public ExploringAction(Party party) {
        this.party = party;
    }

    @Override
    public boolean act(float delta) {

        if (targetNode == null || party.finishedAllActions) {
            Vector2 targetArea = TileFinder.findNearestUnexplored(party.getX() / 16, party.getY() / 16, party);

            if (targetArea == null) {
                // nothing to explore, find doors that are not broken yet

                targetArea = TileFinder.findNearest(party.getX() / 16, party.getY() / 16, Tile.GateClosed);

                if (targetArea == null) {
                    // nothing to do, returning home, end of game
                    // todo end of game
                    return true;
                }
            }

            targetArea.set(targetArea.x, targetArea.y);

            this.targetNode = targetArea;
            party.finishedAllActions = false;
            moveToAndExploreTargetNode(party, targetArea);


        }

        return false;
    }
}
