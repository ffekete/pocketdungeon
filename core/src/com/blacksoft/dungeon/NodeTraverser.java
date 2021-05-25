package com.blacksoft.dungeon;

import com.blacksoft.dungeon.actions.TileCleaner;
import com.blacksoft.state.GameState;

import java.util.ArrayDeque;

import static com.blacksoft.state.Config.MAP_HEIGHT;
import static com.blacksoft.state.Config.MAP_WIDTH;

public class NodeTraverser {

    public static int countRooms() {
        int room = 0;

        int rooms[][] = new int[MAP_WIDTH][MAP_HEIGHT];

        for (int i = 0; i < MAP_WIDTH; i++) {
            for (int j = 0; j < MAP_HEIGHT; j++) {

                if (rooms[i][j] == 0 && TileCleaner.canTraverse(GameState.dungeon, i, j)) {
                    traverse(i, j, ++room, rooms);
                }
            }
        }

        System.out.println("Nr of rooms: " + room);
        return room;
    }

    public static void traverse(int sx, int sy, int roomNumber, int[][] rooms) {

        ArrayDeque<Node> arrayDeque = new ArrayDeque<>();

        boolean[][] visited = new boolean[MAP_WIDTH][MAP_HEIGHT];

        Node start = GameState.dungeon.nodes[sx][sy];

        arrayDeque.add(start);

        while (!arrayDeque.isEmpty()) {

            Node current = arrayDeque.pop();
            visited[(int) current.x][(int) current.y] = true;

            rooms[(int) current.x][(int) current.y] = roomNumber;

            if (current.x != 0 && TileCleaner.canTraverse(GameState.dungeon, (int) current.x - 1, (int) current.y) && !visited[(int) current.x - 1][(int) current.y]) {
                arrayDeque.add(GameState.dungeon.nodes[(int) current.x - 1][(int) current.y]);
            }

            if (current.x != MAP_WIDTH - 1 && TileCleaner.canTraverse(GameState.dungeon, (int) current.x + 1, (int) current.y) && !visited[(int) current.x + 1][(int) current.y]) {
                arrayDeque.add(GameState.dungeon.nodes[(int) current.x + 1][(int) current.y]);
            }

            if (current.y != 0 && TileCleaner.canTraverse(GameState.dungeon, (int) current.x, (int) current.y - 1) && !visited[(int) current.x][(int) current.y - 1]) {
                arrayDeque.add(GameState.dungeon.nodes[(int) current.x][(int) current.y - 1]);
            }

            if (current.y != MAP_HEIGHT - 1 && TileCleaner.canTraverse(GameState.dungeon, (int) current.x, (int) current.y + 1) && !visited[(int) current.x][(int) current.y + 1]) {
                arrayDeque.add(GameState.dungeon.nodes[(int) current.x][(int) current.y + 1]);
            }
        }
    }

}
