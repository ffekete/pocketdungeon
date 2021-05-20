package com.blacksoft.dungeon;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.blacksoft.state.GameState;

import static com.blacksoft.state.Config.MAP_HEIGHT;
import static com.blacksoft.state.Config.MAP_WIDTH;
import static com.blacksoft.state.Config.TEXTURE_SIZE;

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
        if (this.textureRegion == null) {
            currentTextureRegion = GameState.dungeon.nodes[x][y].building.getTextureRegion();
        } else {
            currentTextureRegion = this.textureRegion;
        }

        int v = 0;

        if (x == 0 || GameState.dungeon.nodes[x - 1][y].tile.canMergeWith(tile)) {
            v += 1;
        }

        if ((x == 0 || y >= MAP_HEIGHT - 1) || GameState.dungeon.nodes[x - 1][y + 1].tile.canMergeWith(tile)) {
            v += 2;
        }

        if (y >= MAP_HEIGHT - 1 || GameState.dungeon.nodes[x][y + 1].tile.canMergeWith(tile)) {
            v += 4;
        }

        if (x == MAP_WIDTH - 1 || y == MAP_HEIGHT - 1 || GameState.dungeon.nodes[x + 1][y + 1].tile.canMergeWith(tile)) {
            v += 8;
        }

        if (x == MAP_WIDTH - 1 || GameState.dungeon.nodes[x + 1][y].tile.canMergeWith(tile)) {
            v += 16;
        }

        if (x == MAP_WIDTH - 1 || y == 0 || GameState.dungeon.nodes[x + 1][y - 1].tile.canMergeWith(tile)) {
            v += 32;
        }

        if (y == 0 || GameState.dungeon.nodes[x][y - 1].tile.canMergeWith(tile)) {
            v += 64;
        }

        if (y == 0 || x == 0 || GameState.dungeon.nodes[x - 1][y - 1].tile.canMergeWith(tile)) {
            v += 128;
        }

        // 2   4   8
        // 1   X   16
        // 128 64  32

        // two missing (x-x)
        if (v == 1 + 2 + 4 + 8 + 16 + 32) currentTextureRegion.setRegion(16, 32, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 2 + 4 + 8 + 16 + 128) currentTextureRegion.setRegion(16, 32, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 2 + 4 + 8 + 64 + 128) currentTextureRegion.setRegion(32, 16, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 2 + 4 + 32 + 64 + 128) currentTextureRegion.setRegion(32, 16, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 2 + 16 + 32 + 64 + 128) currentTextureRegion.setRegion(16, 0, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 8 + 16 + 32 + 64 + 128) currentTextureRegion.setRegion(16, 0, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 4 + 8 + 16 + 32 + 64 + 128) currentTextureRegion.setRegion(0, 16, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 2 + 4 + 8 + 16 + 32 + 64) currentTextureRegion.setRegion(0, 16, TEXTURE_SIZE, TEXTURE_SIZE);

        // three missing
        if (v == 8 + 16 + 32 + 64 + 128) currentTextureRegion.setRegion(0, 0, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 16 + 32 + 64 + 128) currentTextureRegion.setRegion(16, 0, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 2 + 32 + 64 + 128) currentTextureRegion.setRegion(32, 0, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 2 + 4 + 64 + 128) currentTextureRegion.setRegion(32, 16, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 2 + 4 + 8 + 128) currentTextureRegion.setRegion(32, 32, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 2 + 4 + 8 + 16) currentTextureRegion.setRegion(16, 32, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 2 + 4 + 8 + 16 + 32) currentTextureRegion.setRegion(0, 32, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 4 + 8 + 16 + 32 + 64) currentTextureRegion.setRegion(0, 16, TEXTURE_SIZE, TEXTURE_SIZE);

        // three missing - not neighbours
        if (v == 1 + 4 + 8 + 16 + 32) currentTextureRegion.setRegion(16, 32, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 2 + 4 + 16 + 128) currentTextureRegion.setRegion(0, 64, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 2 + 4 + 32 + 64) currentTextureRegion.setRegion(64, 48, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 4 + 8 + 64 + 128) currentTextureRegion.setRegion(16, 64, TEXTURE_SIZE, TEXTURE_SIZE);

        // four missing
        if (v == 16 + 32 + 64 + 128) currentTextureRegion.setRegion(0, 0, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 32 + 64 + 128) currentTextureRegion.setRegion(32, 0, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 2 + 64 + 128) currentTextureRegion.setRegion(32, 0, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 2 + 4 + 128) currentTextureRegion.setRegion(16, 32, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 2 + 4 + 8) currentTextureRegion.setRegion(32, 32, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 2 + 4 + 8 + 16) currentTextureRegion.setRegion(0, 32, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 4 + 8 + 16 + 32) currentTextureRegion.setRegion(0, 32, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 8 + 16 + 32 + 64) currentTextureRegion.setRegion(0, 0, TEXTURE_SIZE, TEXTURE_SIZE);
        // four missing - not neighbours
        if (v == 1 + 2 + 16 + 128) currentTextureRegion.setRegion(16, 48, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 8 + 16 + 32) currentTextureRegion.setRegion(16, 48, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 2 + 4 + 8 + 64) currentTextureRegion.setRegion(48, 16, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 4 + 32 + 64 + 128) currentTextureRegion.setRegion(48, 16, TEXTURE_SIZE, TEXTURE_SIZE);

        // five missing - Half circle
        if (v == 32 + 64 + 128) currentTextureRegion.setRegion(48, 0, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 64 + 128) currentTextureRegion.setRegion(32, 0, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 2 + 128) currentTextureRegion.setRegion(0, 16, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 2 + 4) currentTextureRegion.setRegion(32, 32, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 2 + 4 + 8) currentTextureRegion.setRegion(48, 32, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 4 + 8 + 16) currentTextureRegion.setRegion(0, 32, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 8 + 16 + 32) currentTextureRegion.setRegion(0, 48, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 16 + 32 + 64) currentTextureRegion.setRegion(0, 0, TEXTURE_SIZE, TEXTURE_SIZE);
        // five missing - not neighbours
        if (v == 1 + 16 + 128) currentTextureRegion.setRegion(16, 48, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 2 + 16) currentTextureRegion.setRegion(16, 48, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 8 + 16) currentTextureRegion.setRegion(16, 48, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 16 + 32) currentTextureRegion.setRegion(16, 48, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 2 + 4 + 64) currentTextureRegion.setRegion(16, 48, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 4 + 8 + 64) currentTextureRegion.setRegion(48, 16, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 4 + 32 + 64) currentTextureRegion.setRegion(48, 16, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 4 + 64 + 128) currentTextureRegion.setRegion(48, 16, TEXTURE_SIZE, TEXTURE_SIZE);


        // six missing
        if (v == 128 + 1) currentTextureRegion.setRegion(32, 48, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 2) currentTextureRegion.setRegion(32, 48, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 2 + 4) currentTextureRegion.setRegion(48, 32, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 4 + 8) currentTextureRegion.setRegion(48, 32, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 8 + 16) currentTextureRegion.setRegion(0, 48, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 16 + 32) currentTextureRegion.setRegion(0, 48, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 32 + 64) currentTextureRegion.setRegion(48, 0, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 64 + 128) currentTextureRegion.setRegion(48, 0, TEXTURE_SIZE, TEXTURE_SIZE);

        // seven missing
        if (v == 128 + 1) currentTextureRegion.setRegion(32, 48, TEXTURE_SIZE, TEXTURE_SIZE);

        if (v == 1 + 2) currentTextureRegion.setRegion(32, 48, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 2 + 4) currentTextureRegion.setRegion(48, 32, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 4 + 8) currentTextureRegion.setRegion(48, 32, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 8 + 16) currentTextureRegion.setRegion(0, 48, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 16 + 32) currentTextureRegion.setRegion(0, 48, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 32 + 64) currentTextureRegion.setRegion(48, 0, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 64 + 128) currentTextureRegion.setRegion(48, 0, TEXTURE_SIZE, TEXTURE_SIZE);

        // vertical
        if (v == 0) currentTextureRegion.setRegion(48, 48, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1) currentTextureRegion.setRegion(32, 48, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 4) currentTextureRegion.setRegion(48, 32, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 16) currentTextureRegion.setRegion(0, 48, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 64) currentTextureRegion.setRegion(48, 0, TEXTURE_SIZE, TEXTURE_SIZE);

        // "L" shape
        if (v == 1 + 4) currentTextureRegion.setRegion(32, 32, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 4 + 16) currentTextureRegion.setRegion(0, 32, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 16 + 64) currentTextureRegion.setRegion(48, 0, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 64 + 1) currentTextureRegion.setRegion(32, 0, TEXTURE_SIZE, TEXTURE_SIZE);

        // all around except one
        if (v == 2 + 4 + 8 + 16 + 32 + 64 + 128) currentTextureRegion.setRegion(0, 16, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 4 + 8 + 16 + 32 + 64 + 128) currentTextureRegion.setRegion(80, 16, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 2 + 8 + 16 + 32 + 64 + 128) currentTextureRegion.setRegion(16, 0, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 2 + 4 + 16 + 32 + 64 + 128) currentTextureRegion.setRegion(80, 0, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 2 + 4 + 8 + 32 + 64 + 128) currentTextureRegion.setRegion(32, 16, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 2 + 4 + 8 + 16 + 64 + 128) currentTextureRegion.setRegion(80, 32, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 2 + 4 + 8 + 16 + 32 + 128) currentTextureRegion.setRegion(16, 32, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 2 + 4 + 8 + 16 + 32 + 64) currentTextureRegion.setRegion(80, 48, TEXTURE_SIZE, TEXTURE_SIZE);

        // all around
        if (v == 1 + 2 + 4 + 8 + 16 + 32 + 64 + 128) currentTextureRegion.setRegion(16, 16, TEXTURE_SIZE, TEXTURE_SIZE);

        // diagonal missing
        if (v == 1 + 4 + 8 + 16 + 32 + 64 + 128) currentTextureRegion.setRegion(80, 16, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 2 + 4 + 16 + 32 + 64 + 128) currentTextureRegion.setRegion(80, 0, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 2 + 4 + 8 + 16 + 64 + 128) currentTextureRegion.setRegion(32, 16, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 2 + 4 + 8 + 16 + 32 + 64) currentTextureRegion.setRegion(0, 16, TEXTURE_SIZE, TEXTURE_SIZE);

        // 2   4   8
        // 1   X   16
        // 128 64  32

        if (v == 4 + 8 + 16 + 64) currentTextureRegion.setRegion(48, 16, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 4 + 16 + 32 + 64) currentTextureRegion.setRegion(64, 0, TEXTURE_SIZE, TEXTURE_SIZE);

        if (v == 1 + 2 + 4 + 16) currentTextureRegion.setRegion(16, 48, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 16 + 64 + 128) currentTextureRegion.setRegion(16, 48, TEXTURE_SIZE, TEXTURE_SIZE);

        if (v == 2 + 4 + 8 + 64 + 128) currentTextureRegion.setRegion(48, 16, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 2 + 4 + 8 + 32 + 64) currentTextureRegion.setRegion(48, 16, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 4 + 8 + 32 + 64) currentTextureRegion.setRegion(48, 16, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 4 + 8 + 32 + 128) currentTextureRegion.setRegion(48, 16, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 2 + 4 + 8 + 16 + 64) currentTextureRegion.setRegion(48, 16, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 2 + 128) currentTextureRegion.setRegion(32, 48, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 4 + 8 + 64) currentTextureRegion.setRegion(48, 16, TEXTURE_SIZE, TEXTURE_SIZE);

        if (v == 1 + 2 + 16 + 32) currentTextureRegion.setRegion(16, 48, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 2 + 8 + 16) currentTextureRegion.setRegion(16, 48, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 8 + 16 + 128) currentTextureRegion.setRegion(16, 48, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 16 + 32 + 128) currentTextureRegion.setRegion(16, 48, TEXTURE_SIZE, TEXTURE_SIZE);

        // 2   4   8
        // 1   X   16
        // 128 64  32
        if (v == 1 + 2 + 16 + 64 + 128) currentTextureRegion.setRegion(32, 0, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 8 + 16 + 32 + 64) currentTextureRegion.setRegion(0, 0, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 1 + 4 + 32 + 64 + 128) currentTextureRegion.setRegion(64, 16, TEXTURE_SIZE, TEXTURE_SIZE);
        if (v == 4 + 16 + 32 + 64 + 128) currentTextureRegion.setRegion(64, 0, TEXTURE_SIZE, TEXTURE_SIZE);

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

    public GroundTiledMapTile(int x,
                              int y,
                              Tile tile) {
        this.x = x;
        this.y = y;
        this.tile = tile;
    }

    public GroundTiledMapTile(TextureRegion textureRegion,
                              int x,
                              int y,
                              Tile tile) {
        this.textureRegion = textureRegion;
        this.x = x;
        this.y = y;
        this.tile = tile;
    }
}