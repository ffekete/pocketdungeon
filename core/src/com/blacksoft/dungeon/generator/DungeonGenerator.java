package com.blacksoft.dungeon.generator;

import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;

public interface DungeonGenerator {

    void generate(ParallelAction sectorPlacementAction, float delay);

}
