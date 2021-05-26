package com.blacksoft.dungeon;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.blacksoft.state.GameState;

import static com.blacksoft.state.Config.MAP_HEIGHT;
import static com.blacksoft.state.Config.MAP_WIDTH;

/**
 * @brief Represents a non changing {@link TiledMapTile} (can be cached)
 */
public class GroundTiledMapTile implements TiledMapTile {

    public Tile tile;
    int x;
    int y;
    
    private int textureRegionWidth;
    private int  textureRegionHeight;

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
            currentTextureRegion = GameState.dungeon.nodes[x][y].object.getTextureRegion();
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
        if (v == 1 + 2 + 4 + 8 + 16 + 32) currentTextureRegion.setRegion(16, 32, textureRegionWidth,  textureRegionHeight);
        else if (v == 13) currentTextureRegion.setRegion(32, 32, textureRegionWidth,  textureRegionHeight);
        else if (v == 14) currentTextureRegion.setRegion(48, 32, textureRegionWidth,  textureRegionHeight);
        else if (v == 22) currentTextureRegion.setRegion(0, 32, textureRegionWidth,  textureRegionHeight);
        else if (v == 29) currentTextureRegion.setRegion(16, 32, textureRegionWidth,  textureRegionHeight);
        else if (v == 46) currentTextureRegion.setRegion(48, 32, textureRegionWidth,  textureRegionHeight);
        else if (v == 52) currentTextureRegion.setRegion(0, 32, textureRegionWidth,  textureRegionHeight);
        else if (v == 69) currentTextureRegion.setRegion(32, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 84) currentTextureRegion.setRegion(0, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 87) currentTextureRegion.setRegion(16, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 89) currentTextureRegion.setRegion(0, 0, textureRegionWidth,  textureRegionHeight);
        else if (v == 93) currentTextureRegion.setRegion(16, 0, textureRegionWidth,  textureRegionHeight);
        else if (v == 97) currentTextureRegion.setRegion(48, 0, textureRegionWidth,  textureRegionHeight);
        else if (v == 128) currentTextureRegion.setRegion(48, 48, textureRegionWidth,  textureRegionHeight);
        else if (v == 139) currentTextureRegion.setRegion(48, 48, textureRegionWidth,  textureRegionHeight);
        else if (v == 155) currentTextureRegion.setRegion(16, 48, textureRegionWidth,  textureRegionHeight);
        else if (v == 157) currentTextureRegion.setRegion(16, 32, textureRegionWidth,  textureRegionHeight);
        else if (v == 181) currentTextureRegion.setRegion(16, 32, textureRegionWidth,  textureRegionHeight);
        else if (v == 185) currentTextureRegion.setRegion(16, 48, textureRegionWidth,  textureRegionHeight);
        else if (v == 194) currentTextureRegion.setRegion(48, 0, textureRegionWidth,  textureRegionHeight);
        else if (v == 216) currentTextureRegion.setRegion(0, 0, textureRegionWidth,  textureRegionHeight);
        else if (v == 218) currentTextureRegion.setRegion(0, 0, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 2 + 4 + 8 + 16 + 128) currentTextureRegion.setRegion(16, 32, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 2 + 4 + 8 + 64 + 128) currentTextureRegion.setRegion(32, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 2 + 4 + 32 + 64 + 128) currentTextureRegion.setRegion(32, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 2 + 16 + 32 + 64 + 128) currentTextureRegion.setRegion(16, 0, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 8 + 16 + 32 + 64 + 128) currentTextureRegion.setRegion(16, 0, textureRegionWidth,  textureRegionHeight);
        else if (v == 4 + 8 + 16 + 32 + 64 + 128) currentTextureRegion.setRegion(0, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 2 + 4 + 8 + 16 + 32 + 64) currentTextureRegion.setRegion(0, 16, textureRegionWidth,  textureRegionHeight);
            // three missing
        else if (v == 8 + 16 + 32 + 64 + 128) currentTextureRegion.setRegion(0, 0, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 16 + 32 + 64 + 128) currentTextureRegion.setRegion(16, 0, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 2 + 32 + 64 + 128) currentTextureRegion.setRegion(32, 0, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 2 + 4 + 64 + 128) currentTextureRegion.setRegion(32, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 2 + 4 + 8 + 128) currentTextureRegion.setRegion(32, 32, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 2 + 4 + 8 + 16) currentTextureRegion.setRegion(16, 32, textureRegionWidth,  textureRegionHeight);
        else if (v == 2 + 4 + 8 + 16 + 32) currentTextureRegion.setRegion(0, 32, textureRegionWidth,  textureRegionHeight);
        else if (v == 4 + 8 + 16 + 32 + 64) currentTextureRegion.setRegion(0, 16, textureRegionWidth,  textureRegionHeight);
            // three missing - not neighbours
        else if (v == 1 + 4 + 8 + 16 + 32) currentTextureRegion.setRegion(16, 32, textureRegionWidth,  textureRegionHeight);
            // four missing
        else if (v == 16 + 32 + 64 + 128) currentTextureRegion.setRegion(0, 0, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 32 + 64 + 128) currentTextureRegion.setRegion(32, 0, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 2 + 64 + 128) currentTextureRegion.setRegion(32, 0, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 2 + 4 + 128) currentTextureRegion.setRegion(16, 32, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 2 + 4 + 8) currentTextureRegion.setRegion(32, 32, textureRegionWidth,  textureRegionHeight);
        else if (v == 2 + 4 + 8 + 16) currentTextureRegion.setRegion(0, 32, textureRegionWidth,  textureRegionHeight);
        else if (v == 4 + 8 + 16 + 32) currentTextureRegion.setRegion(0, 32, textureRegionWidth,  textureRegionHeight);
        else if (v == 8 + 16 + 32 + 64) currentTextureRegion.setRegion(0, 0, textureRegionWidth,  textureRegionHeight);
            // four missing - not neighbours
        else if (v == 1 + 2 + 16 + 128) currentTextureRegion.setRegion(16, 48, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 8 + 16 + 32) currentTextureRegion.setRegion(16, 48, textureRegionWidth,  textureRegionHeight);
        else if (v == 2 + 4 + 8 + 64) currentTextureRegion.setRegion(48, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 4 + 32 + 64 + 128) currentTextureRegion.setRegion(48, 16, textureRegionWidth,  textureRegionHeight);
            // five missing - Half circle
        else if (v == 32 + 64 + 128) currentTextureRegion.setRegion(48, 0, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 64 + 128) currentTextureRegion.setRegion(32, 0, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 2 + 4) currentTextureRegion.setRegion(32, 32, textureRegionWidth,  textureRegionHeight);
        else if (v == 4 + 8 + 16) currentTextureRegion.setRegion(0, 32, textureRegionWidth,  textureRegionHeight);
        else if (v == 8 + 16 + 32) currentTextureRegion.setRegion(0, 48, textureRegionWidth,  textureRegionHeight);
        else if (v == 16 + 32 + 64) currentTextureRegion.setRegion(0, 0, textureRegionWidth,  textureRegionHeight);
            // five missing - not neighbours
        else if (v == 1 + 16 + 128) currentTextureRegion.setRegion(16, 48, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 2 + 16) currentTextureRegion.setRegion(16, 48, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 8 + 16) currentTextureRegion.setRegion(16, 48, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 16 + 32) currentTextureRegion.setRegion(16, 48, textureRegionWidth,  textureRegionHeight);
        else if (v == 2 + 4 + 64) currentTextureRegion.setRegion(48, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 4 + 8 + 64) currentTextureRegion.setRegion(48, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 4 + 32 + 64) currentTextureRegion.setRegion(48, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 4 + 64 + 128) currentTextureRegion.setRegion(48, 16, textureRegionWidth,  textureRegionHeight);
            // six missing
        else if (v == 128 + 1) currentTextureRegion.setRegion(32, 48, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 2) currentTextureRegion.setRegion(32, 48, textureRegionWidth,  textureRegionHeight);
        else if (v == 2 + 4) currentTextureRegion.setRegion(48, 32, textureRegionWidth,  textureRegionHeight);
        else if (v == 4 + 8) currentTextureRegion.setRegion(48, 32, textureRegionWidth,  textureRegionHeight);
        else if (v == 8 + 16) currentTextureRegion.setRegion(0, 48, textureRegionWidth,  textureRegionHeight);
        else if (v == 16 + 32) currentTextureRegion.setRegion(0, 48, textureRegionWidth,  textureRegionHeight);
        else if (v == 32 + 64) currentTextureRegion.setRegion(48, 0, textureRegionWidth,  textureRegionHeight);
        else if (v == 64 + 128) currentTextureRegion.setRegion(48, 0, textureRegionWidth,  textureRegionHeight);

            // vertical
        else if (v == 0) currentTextureRegion.setRegion(48, 48, textureRegionWidth,  textureRegionHeight);
        else if (v == 1) currentTextureRegion.setRegion(32, 48, textureRegionWidth,  textureRegionHeight);
        else if (v == 4) currentTextureRegion.setRegion(48, 32, textureRegionWidth,  textureRegionHeight);
        else if (v == 16) currentTextureRegion.setRegion(0, 48, textureRegionWidth,  textureRegionHeight);
        else if (v == 64) currentTextureRegion.setRegion(48, 0, textureRegionWidth,  textureRegionHeight);
            // "L" shape
        else if (v == 1 + 4) currentTextureRegion.setRegion(32, 32, textureRegionWidth,  textureRegionHeight);
        else if (v == 4 + 16) currentTextureRegion.setRegion(0, 32, textureRegionWidth,  textureRegionHeight);
        else if (v == 16 + 64) currentTextureRegion.setRegion(48, 0, textureRegionWidth,  textureRegionHeight);
        else if (v == 64 + 1) currentTextureRegion.setRegion(32, 0, textureRegionWidth,  textureRegionHeight);
            // all around except one
        else if (v == 1 + 4 + 8 + 16 + 32 + 64 + 128)
            currentTextureRegion.setRegion(80, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 2 + 8 + 16 + 32 + 64 + 128) currentTextureRegion.setRegion(16, 0, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 2 + 4 + 16 + 32 + 64 + 128) currentTextureRegion.setRegion(80, 0, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 2 + 4 + 8 + 32 + 64 + 128) currentTextureRegion.setRegion(32, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 2 + 4 + 8 + 16 + 32 + 128) currentTextureRegion.setRegion(16, 32, textureRegionWidth,  textureRegionHeight);
            // all around
        else if (v == 1 + 2 + 4 + 8 + 16 + 32 + 64 + 128)
            currentTextureRegion.setRegion(16, 16, textureRegionWidth,  textureRegionHeight);
            // diagonal missing
        else if (v == 1 + 2 + 4 + 8 + 16 + 32 + 64) currentTextureRegion.setRegion(0, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 4 + 8 + 16 + 64) currentTextureRegion.setRegion(48, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 4 + 16 + 32 + 64) currentTextureRegion.setRegion(64, 0, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 2 + 4 + 16) currentTextureRegion.setRegion(16, 48, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 16 + 64 + 128) currentTextureRegion.setRegion(32, 0, textureRegionWidth,  textureRegionHeight);
        else if (v == 2 + 4 + 8 + 64 + 128) currentTextureRegion.setRegion(48, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 2 + 4 + 8 + 32 + 64) currentTextureRegion.setRegion(48, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 4 + 8 + 32 + 64) currentTextureRegion.setRegion(48, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 4 + 8 + 32 + 128) currentTextureRegion.setRegion(48, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 2 + 4 + 8 + 16 + 64) currentTextureRegion.setRegion(48, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 2 + 128) currentTextureRegion.setRegion(32, 48, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 2 + 16 + 32) currentTextureRegion.setRegion(16, 48, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 2 + 8 + 16) currentTextureRegion.setRegion(16, 48, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 8 + 16 + 128) currentTextureRegion.setRegion(16, 48, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 16 + 32 + 128) currentTextureRegion.setRegion(16, 48, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 2 + 16 + 64 + 128) currentTextureRegion.setRegion(32, 0, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 8 + 16 + 32 + 64) currentTextureRegion.setRegion(0, 0, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 4 + 32 + 64 + 128) currentTextureRegion.setRegion(64, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 4 + 16 + 32 + 64 + 128) currentTextureRegion.setRegion(64, 0, textureRegionWidth,  textureRegionHeight);
        else if (v == 4 + 16 + 64 + 128) currentTextureRegion.setRegion(48, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 4 + 128) currentTextureRegion.setRegion(96, 0, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 16 + 32 + 64) currentTextureRegion.setRegion(0, 0, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 4 + 8 + 16 + 32 + 64) currentTextureRegion.setRegion(0, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 4 + 8 + 16 + 64 + 128) currentTextureRegion.setRegion(48, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 2 + 4 + 8 + 16 + 64) currentTextureRegion.setRegion(48, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 2 + 16 + 64) currentTextureRegion.setRegion(48, 0, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 64) currentTextureRegion.setRegion(48, 0, textureRegionWidth,  textureRegionHeight);
        else if (v == 2 + 4 + 64 + 128) currentTextureRegion.setRegion(48, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 2 + 4 + 32 + 64) currentTextureRegion.setRegion(48, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 2 + 4 + 16 + 64 + 128) currentTextureRegion.setRegion(32, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 2 + 4 + 32 + 64 + 128) currentTextureRegion.setRegion(48, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 4 + 64 + 128) currentTextureRegion.setRegion(64, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 2 + 4 + 64) currentTextureRegion.setRegion(48, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 2 + 32 + 64) currentTextureRegion.setRegion(48, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 2 + 8 + 64) currentTextureRegion.setRegion(48, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 2 + 4 + 16 + 128) currentTextureRegion.setRegion(16, 48, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 2 + 4 + 32 + 64) currentTextureRegion.setRegion(48, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 2 + 4 + 16 + 32 + 64 + 128) currentTextureRegion.setRegion(64, 0, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 2 + 4 + 8 + 64) currentTextureRegion.setRegion(48, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 2 + 16 + 32 + 64) currentTextureRegion.setRegion(0, 0, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 2 + 64) currentTextureRegion.setRegion(48, 0, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 4 + 16 + 32 + 64 + 128) currentTextureRegion.setRegion(96, 64, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 8 + 16 + 64 + 128) currentTextureRegion.setRegion(96, 64, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 4 + 8 + 16 + 64 + 128) currentTextureRegion.setRegion(32, 0, textureRegionWidth,  textureRegionHeight);
        else if (v == 2 + 16 + 32 + 64) currentTextureRegion.setRegion(0, 0, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 4 + 8 + 64 + 128) currentTextureRegion.setRegion(64, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 8 + 16 + 64) currentTextureRegion.setRegion(48, 0, textureRegionWidth,  textureRegionHeight);
        else if (v == 1 + 2 + 4 + 8 + 32 + 128) currentTextureRegion.setRegion(32, 0, textureRegionWidth,  textureRegionHeight);

            // 2   4   8
            // 1   X   16
            // 128 64  32

        else if (v == 17) currentTextureRegion.setRegion(16, 48, textureRegionWidth,  textureRegionHeight);
        else if (v == 21) currentTextureRegion.setRegion(16, 0, textureRegionWidth,  textureRegionHeight);
        else if (v == 26) currentTextureRegion.setRegion(0, 32, textureRegionWidth,  textureRegionHeight);
        else if (v == 55) currentTextureRegion.setRegion(16, 32, textureRegionWidth,  textureRegionHeight);
        else if (v == 68) currentTextureRegion.setRegion(48, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 81) currentTextureRegion.setRegion(16, 32, textureRegionWidth,  textureRegionHeight);
        else if (v == 85) currentTextureRegion.setRegion(16, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 134) currentTextureRegion.setRegion(48, 32, textureRegionWidth,  textureRegionHeight);
        else if (v == 161) currentTextureRegion.setRegion(32, 48, textureRegionWidth,  textureRegionHeight);
        else if (v == 187) currentTextureRegion.setRegion(16, 48, textureRegionWidth,  textureRegionHeight);
        else if (v == 200) currentTextureRegion.setRegion(48, 0, textureRegionWidth,  textureRegionHeight);
        else if (v == 222) currentTextureRegion.setRegion(0, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 223) currentTextureRegion.setRegion(32, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 226) currentTextureRegion.setRegion(48, 0, textureRegionWidth,  textureRegionHeight);
        else if (v == 236) currentTextureRegion.setRegion(0, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 238) currentTextureRegion.setRegion(48, 16, textureRegionWidth,  textureRegionHeight);
        else if (v == 254) currentTextureRegion.setRegion(0, 16, textureRegionWidth,  textureRegionHeight);

        
            //128+32+16+8+2+1
        else {
            System.out.println(String.format("Found the culprit: %s %s %s", v, x, y));
        }

        GameState.tileCache[x][y] = v;

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
        this.textureRegionWidth = tile.textureWidth;
        this. textureRegionHeight =  tile.textureHeight;
    }

    public GroundTiledMapTile(TextureRegion textureRegion,
                              int x,
                              int y,
                              Tile tile) {
        this.textureRegion = textureRegion;
        this.x = x;
        this.y = y;
        this.tile = tile;
        this.textureRegionWidth = tile.textureWidth;
        this. textureRegionHeight =  tile.textureHeight;
    }
}