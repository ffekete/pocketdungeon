package com.blacksoft.dungeon;

import java.util.Arrays;
import java.util.List;

import static com.blacksoft.state.Config.TEXTURE_SIZE;

public enum Tile {

    BrickWall(true, true, TEXTURE_SIZE, TEXTURE_SIZE),
    EmptyTiles(false, true, TEXTURE_SIZE, TEXTURE_SIZE);

    static {
        BrickWall.canMergeWith = Arrays.asList(BrickWall);
        EmptyTiles.canMergeWith = Arrays.asList(EmptyTiles);
    }

    private boolean solid;
    private boolean tiled;

    public int textureWidth;
    public int textureHeight;

    private List<Tile> canMergeWith;

    Tile(boolean solid,
         boolean tiled,
         int textureWidth,
         int textureHeight) {
        this.solid = solid;
        this.tiled = tiled;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
    }

    public boolean isSolid() {
        return solid;
    }

    public boolean isTiled() {
        return tiled;
    }

    public boolean canMergeWith(Tile tile) {
        return this.canMergeWith.contains(tile);
    }
}
