package com.blacksoft.dungeon;

import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.blacksoft.dungeon.actions.CameraShakeAction;
import com.blacksoft.dungeon.actions.MoveNodeToCoordAction;
import com.blacksoft.dungeon.templates.SectorTemplate;
import com.blacksoft.state.GameState;

import static com.blacksoft.state.Config.SECTOR_SIZE;

public class SectorPlacer {

    public static void place(SectorTemplate sectorTemplate, int sx, int sy, Dungeon dungeon, ParallelAction mainPlacementActon, float delay) {

        Node[][] nodes = sectorTemplate.toNodeMap();

        SequenceAction fullSequence = new SequenceAction();
        fullSequence.addAction(new DelayAction(delay));
        ParallelAction parallelAction = new ParallelAction();

        for (int i = 0; i < SECTOR_SIZE; i++) {
            for (int j = 0; j < SECTOR_SIZE; j++) {
                if(nodes[i][j].building != null) {
                    dungeon.placeBuilding(sx * SECTOR_SIZE + i, sy * SECTOR_SIZE + j, nodes[i][j].building, nodes[i][j].tile);
                } else {
                    dungeon.replaceTileToNewTile(sx * SECTOR_SIZE + i, sy * SECTOR_SIZE + j, nodes[i][j].tile);
                }

                parallelAction.addAction(new MoveNodeToCoordAction(dungeon.nodes[sx * SECTOR_SIZE + i][sy * SECTOR_SIZE + j], 0, 400));
            }
        }

        fullSequence.addAction(parallelAction);
        fullSequence.addAction(new CameraShakeAction(0.1f));
        mainPlacementActon.addAction(fullSequence);

    }

}
