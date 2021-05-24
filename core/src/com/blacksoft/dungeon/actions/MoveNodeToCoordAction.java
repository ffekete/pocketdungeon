package com.blacksoft.dungeon.actions;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.blacksoft.dungeon.Node;
import com.blacksoft.state.GameState;

import static com.blacksoft.dungeon.Dungeon.DUNGEON_LAYER;

public class MoveNodeToCoordAction extends MoveByAction {

    private TiledMapTileLayer.Cell cell;

    private float ty;

    public MoveNodeToCoordAction(Node node, float tx, float ty) {

        TiledMapTileLayer layer = (TiledMapTileLayer) GameState.dungeon.tiledMap.getLayers().get(DUNGEON_LAYER);
        this.cell = layer.getCell((int) node.x, (int) node.y);
        cell.getTile().setOffsetY(ty);
        this.setDuration(0.1f);
        this.ty = ty;
    }

    @Override
    protected void update(float percent) {
        super.update(percent);
        cell.getTile().setOffsetY(ty - ty * percent);
    }
}
