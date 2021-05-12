package com.blacksoft.creature.util;

import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.math.Vector2;
import com.blacksoft.creature.Creature;
import com.blacksoft.creature.action.MoveToTileAction;
import com.blacksoft.creature.action.ResetCreatureActionsAction;
import com.blacksoft.creature.action.RestAction;
import com.blacksoft.dungeon.Node;
import com.blacksoft.state.GameState;

public class CreatureActionsUtil {

    public static void moveToTargetNode(Creature creature,
                                  Vector2 target) {
        GraphPath<Node> path = GameState.cityGraph.findPath(GameState.dungeon.getNode(creature.getX() / 16, creature.getY() / 16),
                GameState.dungeon.getNode(target.x, target.y));

        resetCreatureActions(creature);
        creature.finishedAllActions = false;
        for (int i = 1; i < path.getCount(); i++) {
            Node node = path.get(i);
            MoveToTileAction moveToTileAction = new MoveToTileAction(creature, new Vector2(node.x * 16, node.y * 16));
            moveToTileAction.setActor(creature);
            creature.sequenceActions.addAction(moveToTileAction);
        }
        creature.sequenceActions.addAction(new RestAction(creature));
        creature.sequenceActions.addAction(new ResetCreatureActionsAction(creature));
    }

    public static void resetCreatureActions(Creature creature) {
        creature.sequenceActions.reset();
        creature.addAction(creature.sequenceActions);
        creature.finishedAllActions = true;
    }
}
