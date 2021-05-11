package com.blacksoft.dungeon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.blacksoft.dungeon.actions.TileCleaner;
import com.blacksoft.dungeon.building.Building;
import com.blacksoft.dungeon.building.Torch;
import com.blacksoft.state.GameState;

import java.util.ArrayList;
import java.util.List;

import static com.blacksoft.state.Config.MAP_HEIGHT;
import static com.blacksoft.state.Config.MAP_WIDTH;
import static com.blacksoft.state.Config.TEXTURE_SIZE;

public class Dungeon {

    public static final String BUILD_INDICATORS_LAYER = "buildIndicators";
    public static final String DUNGEON_LAYER = "dungeon";

    private static final Tile DEFAULT_TILE = Tile.Rock;

    public Node[][] nodes = new Node[MAP_WIDTH][MAP_HEIGHT];

    public TiledMap tiledMap;

    public Dungeon() {

        tiledMap = new TiledMap();

        TiledMapTileLayer layer = addLayer(DUNGEON_LAYER);
        TiledMapTileLayer buildIndicatorLayer = addLayer(BUILD_INDICATORS_LAYER);

        for (int i = 0; i < MAP_WIDTH; i++) {
            for (int j = 0; j < MAP_HEIGHT; j++) {
                nodes[i][j] = new Node();

                nodes[i][j].tile = DEFAULT_TILE;
                nodes[i][j].x = i;
                nodes[i][j].y = j;

                GroundTiledMapTile tile = new GroundTiledMapTile(new TextureRegion(new Texture(Gdx.files.internal("tile/Rock.png"))), i, j, Tile.Rock);
                TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                cell.setTile(tile);
                layer.setCell(i, j, cell);
            }
        }

        //TileCleaner.clean(this, 0, MAP_HEIGHT / 2);

        placeBuilding((int) 0, MAP_HEIGHT / 2, new Torch(), Tile.Torch);
    }

    private TiledMapTileLayer addLayer(String name) {
        TiledMapTileLayer layer = new TiledMapTileLayer(MAP_WIDTH, MAP_HEIGHT, TEXTURE_SIZE, TEXTURE_SIZE);
        layer.setName(name);
        tiledMap.getLayers().add(layer);
        return layer;
    }

    public void replaceTile(int x,
                            int y,
                            Tile target) {
        TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get(DUNGEON_LAYER);
        nodes[x][y].tile = target;
        GroundTiledMapTile tile = new GroundTiledMapTile(new TextureRegion(new Texture(Gdx.files.internal(String.format("tile/%s.png", target)))), x, y, target);
        TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
        cell.setTile(tile);
        layer.setCell(x, y, cell);
    }

    public void placeBuilding(int x, int y, Building building, Tile tile) {
        replaceTile(x,y,tile);
        building.place(x * 16,y * 16);
    }
}
