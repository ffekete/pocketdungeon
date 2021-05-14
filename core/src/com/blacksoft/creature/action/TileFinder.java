package com.blacksoft.creature.action;

import com.badlogic.gdx.math.Vector2;
import com.blacksoft.dungeon.Node;
import com.blacksoft.dungeon.Tile;
import com.blacksoft.state.GameState;

import java.util.ArrayDeque;
import java.util.Deque;

import static com.blacksoft.state.Config.MAP_HEIGHT;
import static com.blacksoft.state.Config.MAP_WIDTH;

public class TileFinder {

    public static Vector2 findNearest(float x, float y, Tile tile) {

        Node[][] nodes = GameState.dungeon.nodes;
        boolean[][] visited = new boolean[MAP_WIDTH][MAP_HEIGHT];

        Vector2 start = new Vector2(x,y);

        Deque<Vector2> queue = new ArrayDeque<>();

        queue.add(start);

        while (!queue.isEmpty()) {

            Vector2 current = queue.pop();

            int vx = (int)current.x;
            int vy = (int)current.y;

            visited[vx][vy] = true;

            if(nodes[vx][vy].tile == tile) {
                return current;
            }

            addNext(vx-1, vy, queue, visited);
            addNext(vx+1, vy, queue, visited);
            addNext(vx, vy-1, queue, visited);
            addNext(vx, vy+1, queue, visited);
        }

        return null;
    }

    private static void addNext(int x,
                                int y,
                                Deque<Vector2> queue,
                                boolean[][] visited) {
        if(x >= 0 && x < MAP_WIDTH && y >= 0 && y < MAP_HEIGHT && !visited[x][y] && !GameState.dungeon.nodes[x][y].tile.isSolid()) {
            queue.add(new Vector2(x,y));
        }
    }

}
