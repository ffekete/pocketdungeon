package com.blacksoft.dungeon.generator;

import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.blacksoft.dungeon.Dungeon;
import com.blacksoft.dungeon.Tile;
import com.blacksoft.dungeon.sector.SectorPlacer;
import com.blacksoft.dungeon.sector.SectorSelector;
import com.blacksoft.dungeon.sector.templates.Entrance;
import com.blacksoft.dungeon.sector.templates.SectorTemplate;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;

public class CastleDungeonGenerator implements DungeonGenerator {

    @Override
    public void generate(ParallelAction sectorPlacementAction, float delay) {

        GameState.baseEmptyTile = Tile.EmptyTiles;
        GameState.baseWallTile = Tile.BrickWall;

        Dungeon dungeon = new Dungeon();
        GameState.dungeon = dungeon;

        SectorSelector sectorSelector = new SectorSelector();

        for (int i = 0; i < Config.MAP_HEIGHT / Config.SECTOR_SIZE; i++) {
            for (int j = 0; j < Config.MAP_WIDTH / Config.SECTOR_SIZE; j++) {
                if (j == 0 && i == 2) {
                    SectorPlacer.place(new Entrance(), 0, 2, dungeon, sectorPlacementAction, delay);
                } else {
                    SectorTemplate sectorTemplate = sectorSelector.findOne(j, i);
                    SectorPlacer.place(sectorTemplate, j, i, dungeon, sectorPlacementAction, delay);
                }
                delay += 0.35f;
                //sectorPlacementAction.addAction(new DelayAction(0.1f));
            }
        }
    }
}
