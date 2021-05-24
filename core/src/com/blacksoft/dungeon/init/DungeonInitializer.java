package com.blacksoft.dungeon.init;

import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.blacksoft.dungeon.Dungeon;
import com.blacksoft.dungeon.SectorPlacer;
import com.blacksoft.dungeon.templates.Entrance;
import com.blacksoft.dungeon.templates.FourWayCorridor;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;

public class DungeonInitializer {

    public static void init(Dungeon dungeon) {

        ParallelAction sectorPlacementAction = new ParallelAction();

        float delay = 0.35f;

        for (int i = 0; i < Config.MAP_HEIGHT / Config.SECTOR_SIZE; i++) {
            for (int j = 0; j < Config.MAP_WIDTH / Config.SECTOR_SIZE; j++) {
                if(j == 0 && i == 2) {
                    SectorPlacer.place(new Entrance(), 0, 2, dungeon, sectorPlacementAction, delay);
                } else {
                    SectorPlacer.place(new FourWayCorridor(), j, i, dungeon, sectorPlacementAction, delay);
                }
                delay += 0.35f;
                //sectorPlacementAction.addAction(new DelayAction(0.1f));
            }
        }

        GameState.stage.addAction(sectorPlacementAction);


    }
}
