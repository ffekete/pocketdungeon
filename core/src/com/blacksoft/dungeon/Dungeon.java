package com.blacksoft.dungeon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.blacksoft.dungeon.building.Building;
import com.blacksoft.dungeon.building.DungeonEntrance;
import com.blacksoft.dungeon.building.Torch;
import com.blacksoft.dungeon.pathfinder.CityGraph;
import com.blacksoft.state.GameState;

import java.util.ArrayList;
import java.util.List;

import static com.blacksoft.state.Config.DUNGEON_ENTRANCE_LOCATION;
import static com.blacksoft.state.Config.MAP_HEIGHT;
import static com.blacksoft.state.Config.MAP_WIDTH;
import static com.blacksoft.state.Config.TEXTURE_SIZE;

public class Dungeon {

    public static final String BUILD_INDICATORS_LAYER = "buildIndicators";
    public static final String DUNGEON_LAYER = "dungeon";

    private static final Tile DEFAULT_TILE = Tile.Rock;

    public Node[][] nodes = new Node[MAP_WIDTH][MAP_HEIGHT];

    public TiledMap tiledMap;

    public List<Street> streets = new ArrayList<>();
    public List<Node> cities = new ArrayList<>();
    public int nextIndex = 0;
    public ObjectMap<Node, Array<Connection<Node>>> streetsMap = new ObjectMap<>();

    public Dungeon() {

        tiledMap = new TiledMap();

        TiledMapTileLayer layer = addLayer(DUNGEON_LAYER);
        TiledMapTileLayer buildIndicatorLayer = addLayer(BUILD_INDICATORS_LAYER);

        for (int i = 0; i < MAP_WIDTH; i++) {
            for (int j = 0; j < MAP_HEIGHT; j++) {
                nodes[i][j] = new Node();
                nodes[i][j].index = nextIndex;
                nextIndex++;
                cities.add(nodes[i][j]);

                nodes[i][j].tile = DEFAULT_TILE;
                nodes[i][j].x = i;
                nodes[i][j].y = j;

                GroundTiledMapTile tile = new GroundTiledMapTile(new TextureRegion(new Texture(Gdx.files.internal("tile/Rock.png"))), i, j, Tile.Rock);
                TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                cell.setTile(tile);
                layer.setCell(i, j, cell);
            }
        }

        placeBuilding((int) DUNGEON_ENTRANCE_LOCATION.x, (int) DUNGEON_ENTRANCE_LOCATION.y, new DungeonEntrance(), Tile.DungeonEntrance);
        placeBuilding((int) DUNGEON_ENTRANCE_LOCATION.x, (int) DUNGEON_ENTRANCE_LOCATION.y + 1, new Torch(), Tile.Torch);
        placeBuilding((int) DUNGEON_ENTRANCE_LOCATION.x, (int) DUNGEON_ENTRANCE_LOCATION.y - 1, new Torch(), Tile.Torch);

        GameState.cityGraph = new CityGraph();
    }

    private TiledMapTileLayer addLayer(String name) {
        TiledMapTileLayer layer = new TiledMapTileLayer(MAP_WIDTH, MAP_HEIGHT, TEXTURE_SIZE, TEXTURE_SIZE);
        layer.setName(name);
        tiledMap.getLayers().add(layer);
        return layer;
    }

    public void replaceTileToBuilding(int x,
                                      int y,
                                      Tile target) {
        TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get(DUNGEON_LAYER);
        nodes[x][y].tile = target;

        TiledMapTile tile;
        if (target.isTiled()) {
            tile = new GroundTiledMapTile(x, y, target);
        } else {
            tile = new StaticTiledMapTile(new TextureRegion(new Texture(Gdx.files.internal(String.format("tile/%s.png", target)))));
        }
        TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
        cell.setTile(tile);
        layer.setCell(x, y, cell);
    }

    public void placeBuilding(int x,
                              int y,
                              Building building,
                              Tile tile) {
        replaceTileToBuilding(x, y, tile);
        nodes[x][y].building = building;
        building.place(x * 16, y * 16);
    }

    public Node getNode(float x, float y) {
        int vx = (int)x;
        int vy = (int)y;

        return nodes[vx][vy];
    }
}
