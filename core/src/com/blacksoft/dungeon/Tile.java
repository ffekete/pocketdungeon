package com.blacksoft.dungeon;

import java.util.Arrays;
import java.util.List;

import static com.blacksoft.state.Config.TEXTURE_SIZE;

public enum Tile {

    Rock(true, true, TEXTURE_SIZE, TEXTURE_SIZE),
    Empty(false, true, TEXTURE_SIZE, TEXTURE_SIZE),
    Grave(false, false, TEXTURE_SIZE, TEXTURE_SIZE * 2),
    Torch(true, true, TEXTURE_SIZE, TEXTURE_SIZE),
    MysteriousCoffin(false, false, TEXTURE_SIZE, TEXTURE_SIZE),
    Treasury(false, false, TEXTURE_SIZE, TEXTURE_SIZE),
    DungeonEntrance(false, false, TEXTURE_SIZE, TEXTURE_SIZE * 2),
    RestingArea(false, true, TEXTURE_SIZE, TEXTURE_SIZE),
    GateClosed(true, true, TEXTURE_SIZE, TEXTURE_SIZE * 2),
    GateOpened(false, true, TEXTURE_SIZE * 2, TEXTURE_SIZE * 2),
    Library(false, false, TEXTURE_SIZE, TEXTURE_SIZE * 2);

    static {
        Rock.canMergeWith = Arrays.asList(Torch, Rock);
        Empty.canMergeWith = Arrays.asList(Empty, DungeonEntrance, Treasury, GateOpened, GateClosed, Library);
        Library.canMergeWith = Arrays.asList(Empty, DungeonEntrance, Treasury, Library);
        Grave.canMergeWith = Arrays.asList(Grave);
        Torch.canMergeWith = Arrays.asList(Rock, Torch);
        MysteriousCoffin.canMergeWith = Empty.canMergeWith;
        Treasury.canMergeWith = Library.canMergeWith;
        DungeonEntrance.canMergeWith = Library.canMergeWith;
        RestingArea.canMergeWith = Arrays.asList(RestingArea);
        GateOpened.canMergeWith = Arrays.asList(Empty);
        GateClosed.canMergeWith = Arrays.asList(Empty);
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
