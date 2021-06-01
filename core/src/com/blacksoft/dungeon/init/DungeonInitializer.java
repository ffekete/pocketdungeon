package com.blacksoft.dungeon.init;

import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.blacksoft.dungeon.NodeTraverser;
import com.blacksoft.dungeon.generator.DungeonGenerator;

public class DungeonInitializer {

    public static void init(SequenceAction sequenceAction, DungeonGenerator dungeonGenerator) {

        ParallelAction sectorPlacementAction;

        long s = System.currentTimeMillis();

        sectorPlacementAction = new ParallelAction();

        float delay = 0.35f;

        dungeonGenerator.generate(sectorPlacementAction, delay);

        System.out.println("overall elapsed time during dungeon creation: " + (System.currentTimeMillis() - s));

        NodeTraverser.countRooms();

        sequenceAction.addAction(sectorPlacementAction);
    }
}
