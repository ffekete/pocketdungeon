package com.blacksoft.dungeon.objects.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.blacksoft.dungeon.Dungeon;
import com.blacksoft.dungeon.Node;
import com.blacksoft.state.GameState;

import static com.blacksoft.state.Config.*;

public class PlaceObjectsAction extends Action {

    private int i, j;

    public PlaceObjectsAction(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public boolean act(float delta) {

        Dungeon dungeon = GameState.dungeon;
        Node[][] nodes = dungeon.nodes;

        if (nodes[i][j].object != null) {
            nodes[i][j].object.place((i) * TEXTURE_SIZE, (j) * TEXTURE_SIZE);
        }

        return true;
    }
}
