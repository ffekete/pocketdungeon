package com.blacksoft.dungeon;

import java.util.Arrays;
import java.util.List;

public enum Tile {

    Rock(true, true),
    Empty(false, true),
    GraveYard(false, true),
    Torch(true, true),
    VampireCoffin(false, true),
    Treasury(false, true),
    DungeonEntrance(false, false),
    RestingArea(false, true),
    GateClosed(true, true),
    GateOpened(false, true),
    Library(false, true);

    static {
        Rock.canMergeWith = Arrays.asList(Torch, Rock);
        Empty.canMergeWith = Arrays.asList(Empty, DungeonEntrance, Treasury, GateOpened, GateClosed, Library);
        Library.canMergeWith = Arrays.asList(Empty, DungeonEntrance, Treasury, Library);
        GraveYard.canMergeWith = Arrays.asList(GraveYard);
        Torch.canMergeWith = Arrays.asList(Rock, Torch);
        VampireCoffin.canMergeWith = Empty.canMergeWith;
        Treasury.canMergeWith = Library.canMergeWith;
        DungeonEntrance.canMergeWith = Library.canMergeWith;
        RestingArea.canMergeWith = Arrays.asList(RestingArea);
        GateOpened.canMergeWith = Arrays.asList(Empty);
        GateClosed.canMergeWith = Arrays.asList(Empty);
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
