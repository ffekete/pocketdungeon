package com.blacksoft.dungeon;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.blacksoft.state.GameState;

import static com.blacksoft.dungeon.Tile.Rock;

/**
 * @brief Represents a non changing {@link TiledMapTile} (can be cached)
 */
public class GroundTiledMapTile implements TiledMapTile {

    public Tile tile;
    int x;
    int y;

    private int id;

    private BlendMode blendMode = BlendMode.ALPHA;

    private MapProperties properties;

    private MapObjects objects;

    private TextureRegion textureRegion;

    private float offsetX;

    private float offsetY;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public BlendMode getBlendMode() {
        return blendMode;
    }

    @Override
    public void setBlendMode(BlendMode blendMode) {
        this.blendMode = blendMode;
    }

    @Override
    public MapProperties getProperties() {
        if (properties == null) {
            properties = new MapProperties();
        }
        return properties;
    }

    @Override
    public MapObjects getObjects() {
        if (objects == null) {
            objects = new MapObjects();
        }
        return objects;
    }

    @Override
    public TextureRegion getTextureRegion() {
        TextureRegion currentTextureRegion;
        if(this.textureRegion == null) {
            currentTextureRegion = GameState.dungeon.nodes[x][y].building.getTextureRegion();
        } else {
            currentTextureRegion = this.textureRegion;
        }

        int v = 0;
        if(x == 0 || (x > 0 && GameState.dungeon.nodes[x-1][y].tile.canMergeWith(tile))) {
            v += 1;
        }

        if(y == 0 || (y > 0 && GameState.dungeon.nodes[x][y-1].tile.canMergeWith(tile))) {
            v += 8;
        }

        if(x == GameState.dungeon.nodes.length -1 || (x < GameState.dungeon.nodes.length-1 && GameState.dungeon.nodes[x+1][y].tile.canMergeWith(tile))) {
            v += 4;
        }

        if(y == GameState.dungeon.nodes[0].length-1 || (y < GameState.dungeon.nodes[0].length-1 && GameState.dungeon.nodes[x][y+1].tile.canMergeWith(tile))) {
            v += 2;
        }

        if(v == 0) currentTextureRegion.setRegion(48, 48, 16 ,16);
        if(v == 1) currentTextureRegion.setRegion(32, 48, 16 ,16);
        if(v == 2) currentTextureRegion.setRegion(48, 32, 16 ,16);
        if(v == 3) currentTextureRegion.setRegion(32, 32, 16 ,16);
        if(v == 4) currentTextureRegion.setRegion(0, 48, 16 ,16);
        if(v == 5) currentTextureRegion.setRegion(16, 48, 16 ,16);
        if(v == 6) currentTextureRegion.setRegion(0, 32, 16 ,16);
        if(v == 7) currentTextureRegion.setRegion(16, 32, 16 ,16);
        if(v == 8) currentTextureRegion.setRegion(48, 0, 16 ,16);
        if(v == 9) currentTextureRegion.setRegion(32, 0, 16 ,16);
        if(v == 10) currentTextureRegion.setRegion(48, 16, 16 ,16);
        if(v == 11) currentTextureRegion.setRegion(32, 16, 16 ,16);
        if(v == 12) currentTextureRegion.setRegion(0, 0, 16 ,16);
        if(v == 13) currentTextureRegion.setRegion(16, 0, 16 ,16);
        if(v == 14) currentTextureRegion.setRegion(0, 16, 16 ,16);
        if(v == 15) currentTextureRegion.setRegion(16, 16, 16 ,16);


        return currentTextureRegion;
    }

    @Override
    public void setTextureRegion(TextureRegion textureRegion) {

    }

    @Override
    public float getOffsetX() {
        return offsetX;
    }

    @Override
    public void setOffsetX(float offsetX) {
        this.offsetX = offsetX;
    }

    @Override
    public float getOffsetY() {
        return offsetY;
    }

    @Override
    public void setOffsetY(float offsetY) {
        this.offsetY = offsetY;
    }

    public GroundTiledMapTile(int x, int y, Tile tile) {
        this.x = x;
        this.y = y;
        this.tile = tile;
    }

    public GroundTiledMapTile(TextureRegion textureRegion, int x, int y, Tile tile) {
        this.textureRegion = textureRegion;
        this.x = x;
        this.y = y;
        this.tile = tile;
    }
}