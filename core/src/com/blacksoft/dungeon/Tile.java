package com.blacksoft.dungeon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Tile {

    Rock(true),
    Empty(false),
    GraveYard(false),
    Torch(true),
    BloodPool(false);

    static {
        Rock.canMergeWith = Arrays.asList(Torch, Rock);
        Empty.canMergeWith = Arrays.asList(Empty);
        GraveYard.canMergeWith = Arrays.asList(GraveYard);
        Torch.canMergeWith = Arrays.asList(Rock, Torch);
        BloodPool.canMergeWith = Arrays.asList(BloodPool);
    }

    private boolean solid;

    private List<Tile> canMergeWith;

    Tile(boolean solid) {
        this.solid = solid;
    }

    public boolean isSolid() {
        return solid;
    }
    public boolean canMergeWith(Tile tile) {
        return this.canMergeWith.contains(tile);
    }
}
