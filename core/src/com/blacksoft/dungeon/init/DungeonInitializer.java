package com.blacksoft.dungeon.init;

import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.blacksoft.dungeon.Dungeon;
import com.blacksoft.dungeon.NodeTraverser;
import com.blacksoft.dungeon.generator.CastleDungeonGenerator;
import com.blacksoft.dungeon.generator.DungeonGenerator;
import com.blacksoft.dungeon.sector.SectorPlacer;
import com.blacksoft.dungeon.sector.SectorSelector;
import com.blacksoft.dungeon.sector.templates.Entrance;
import com.blacksoft.dungeon.sector.templates.SectorTemplate;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;

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
