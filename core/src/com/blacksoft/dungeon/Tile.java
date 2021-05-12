package com.blacksoft.dungeon;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Tile {

    Rock(true, true),
    Empty(false, true),
    GraveYard(false, true),
    Torch(true, true),
    BloodPool(false, true),
    Treasury(false, true),
    DungeonEntrance(false, false),
    RestingArea(false, true);

    static {
        Rock.canMergeWith = Arrays.asList(Torch, Rock);
        Empty.canMergeWith = Arrays.asList(Empty, DungeonEntrance, Treasury, RestingArea);
        GraveYard.canMergeWith = Arrays.asList(GraveYard);
        Torch.canMergeWith = Arrays.asList(Rock, Torch);
        BloodPool.canMergeWith = Arrays.asList(BloodPool);
        Treasury.canMergeWith = Arrays.asList(Treasury, Empty);
        DungeonEntrance.canMergeWith = Empty.canMergeWith;
        RestingArea.canMergeWith = Empty.canMergeWith;
    }

    private boolean solid;
    private boolean tiled;

    private List<Tile> canMergeWith;

    Tile(boolean solid,
         boolean tiled) {
        this.solid = solid;
        this.tiled = tiled;
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
