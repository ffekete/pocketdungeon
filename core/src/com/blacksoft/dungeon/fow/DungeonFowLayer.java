package com.blacksoft.dungeon.fow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.utils.Array;
import com.blacksoft.dungeon.Dungeon;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;

import static com.blacksoft.state.Config.*;

public class DungeonFowLayer {

    public static TextureRegion unexploredAreaCoverTexture = new TextureRegion(new Texture(Gdx.files.internal("tile/UnexploredArea.png")));
    public static Animation<TextureRegion> unexploredAreaCoverAnimation = new Animation<TextureRegion>(0.3f, TextureRegion.split(unexploredAreaCoverTexture.getTexture(), TEXTURE_SIZE, TEXTURE_SIZE)[0]);


    public boolean[][] explored = new boolean[Config.MAP_WIDTH][Config.MAP_HEIGHT];

    public DungeonFowLayer() {

        for (int i = 0; i < MAP_WIDTH; i++) {
            for (int j = 0; j < MAP_HEIGHT; j++) {
                TiledMapTileLayer tiledMapTileLayer = (TiledMapTileLayer) GameState.dungeon.tiledMap.getLayers().get(Dungeon.FOW_LAYER);
                TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();

                Array<StaticTiledMapTile> tiles = new Array<>();
                for (TextureRegion textureRegion: unexploredAreaCoverAnimation.getKeyFrames()) {
                    tiles.add(new StaticTiledMapTile(textureRegion));
                }

                cell.setTile(new AnimatedTiledMapTile(0.3f, tiles));
                tiledMapTileLayer.setCell(i,j, cell);
            }
        }

    }

    public void calculate() {

    }

}
