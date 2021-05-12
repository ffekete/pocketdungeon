package com.blacksoft.dungeon.pathfinder;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.utils.Array;
import com.blacksoft.dungeon.Node;
import com.blacksoft.state.GameState;

public class CityGraph implements IndexedGraph<Node> {

    CityHeuristic cityHeuristic = new CityHeuristic();

    @Override
    public int getIndex(Node node) {
        return node.index;
    }

    @Override
    public int getNodeCount() {
        return GameState.dungeon.nextIndex;
    }

    @Override
    public Array<Connection<Node>> getConnections(Node fromNode) {
        if(GameState.dungeon.streetsMap.containsKey(fromNode)){
            return GameState.dungeon.streetsMap.get(fromNode);
        }

        return new Array<>(0);
    }

    public GraphPath<Node> findPath(Node startCity, Node goalCity){
        GraphPath<Node> cityPath = new DefaultGraphPath<>();
        new IndexedAStarPathFinder<>(this).searchNodePath(startCity, goalCity, cityHeuristic, cityPath);
        return cityPath;
    }
}