package com.blacksoft.dungeon.init;

import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.blacksoft.dungeon.Dungeon;
import com.blacksoft.dungeon.SectorPlacer;
import com.blacksoft.dungeon.actions.CameraShakeAction;
import com.blacksoft.dungeon.templates.Entrance;
import com.blacksoft.dungeon.templates.FourWayCorridor;
import com.blacksoft.dungeon.templates.TwoWayCorridor;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;

public class DungeonInitializer {

    public static void init(Dungeon dungeon) {

        SequenceAction sectorPlacementAction = new SequenceAction();

        for (int i = 0; i < Config.MAP_WIDTH / Config.SECTOR_SIZE; i++) {
            for (int j = 0; j < Config.MAP_HEIGHT / Config.SECTOR_SIZE; j++) {
                if(i == 0 && j == 2) {
                    SectorPlacer.place(new Entrance(), 0, 2, dungeon, sectorPlacementAction);
                } else {
                    SectorPlacer.place(new TwoWayCorridor(), i, j, dungeon, sectorPlacementAction);
                }
                sectorPlacementAction.addAction(new CameraShakeAction(0.05f));
                sectorPlacementAction.addAction(new DelayAction(0.1f));
            }
        }

        GameState.stage.addAction(sectorPlacementAction);


    }
}
