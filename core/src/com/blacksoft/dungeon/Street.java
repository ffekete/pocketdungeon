package com.blacksoft.dungeon;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.math.Vector2;

public class Street implements Connection<Node> {

    public Node from;
    public Node to;
    public float cost;

    public Street(Node from,
                  Node to) {
        this.from = from;
        this.to = to;
        cost = Vector2.dst(from.x, from.y, from.x, to.y);
    }

    @Override
    public float getCost() {
        return cost;
    }

    @Override
    public Node getFromNode() {
        return from;
    }

    @Override
    public Node getToNode() {
        return to;
    }
}
