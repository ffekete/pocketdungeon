package com.blacksoft.dungeon.actions;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.blacksoft.dungeon.Dungeon;
import com.blacksoft.dungeon.Tile;
import com.blacksoft.dungeon.objects.AbstractMapObject;
import com.blacksoft.dungeon.objects.GameObject;
import com.blacksoft.dungeon.objects.floor.Door;
import com.blacksoft.state.GameState;

import java.util.ArrayList;
import java.util.List;

import static com.blacksoft.state.Config.MAP_HEIGHT;
import static com.blacksoft.state.Config.MAP_WIDTH;

public class TileTypeDetector {

    public static boolean canTraverse(Dungeon dungeon,
                                      int x,
                                      int y) {

        if (x < 0 || y < 0 || x >= MAP_WIDTH || y >= MAP_HEIGHT) {
            return false;
        }

        return !dungeon.nodes[x][y].tile.isSolid();
    }

    public static boolean isWall(Dungeon dungeon,
                                 int x,
                                 int y) {

        if (x < 0 || y < 0 || x >= MAP_WIDTH || y >= MAP_HEIGHT) {
            return false;
        }

        return dungeon.nodes[x][y].tile == GameState.baseWallTile;
    }

    public static <T extends MapObject> T getObject(Dungeon dungeon,
                                                    String layer,
                                                    int x,
                                                    int y,
                                                    Class<T> clazz) {

        if (x < 0 || y < 0 || x >= MAP_WIDTH || y >= MAP_HEIGHT) {
            return null;
        }

        TiledMapTileLayer tiledMapTileLayer = (TiledMapTileLayer) dungeon.tiledMap.getLayers().get(layer);

        for (MapObject object : tiledMapTileLayer.getCell(x, y).getTile().getObjects()) {
            if (clazz.isAssignableFrom(object.getClass())) {
                return (T) object;
            }
        }

        return null;
    }

    public static List<GameObject> getObjects(Dungeon dungeon,
                                                           String layer,
                                                           int x,
                                                           int y) {

        if (x < 0 || y < 0 || x >= MAP_WIDTH || y >= MAP_HEIGHT) {
            return null;
        }

        TiledMapTileLayer tiledMapTileLayer = (TiledMapTileLayer) dungeon.tiledMap.getLayers().get(layer);

        List<GameObject> objects = new ArrayList<>();

        for(MapObject o : tiledMapTileLayer.getCell(x, y).getTile().getObjects()) {
            objects.add((GameObject)o);
        }
        return objects;
    }

    public static boolean hasAnyBlockingObjects(Dungeon dungeon,
                                                String layer,
                                                int x,
                                                int y) {

        if (x < 0 || y < 0 || x >= MAP_WIDTH || y >= MAP_HEIGHT) {
            return false;
        }

        TiledMapTileLayer tiledMapTileLayer = (TiledMapTileLayer) dungeon.tiledMap.getLayers().get(layer);
        for (MapObject object : tiledMapTileLayer.getCell(x, y).getTile().getObjects()) {
            if(((AbstractMapObject)object).blocking) {
                return true;
            }
        }
        return false;
    }
}
