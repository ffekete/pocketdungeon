package com.blacksoft.dungeon.sector.templates.twoways.room.twoway;

import com.blacksoft.dungeon.sector.templates.SectorTemplate;

import java.util.Arrays;
import java.util.List;

public class TwoWayRoomD extends SectorTemplate {

    private Character[][] sectorMap = new Character[][]{
            {'W', 'T', 'W', 'T', 'W'},
            {'W', '@', '.', '@', 'W'},
            {'.', '.', '.', '.', 'W'},
            {'W', '.', '.', '.', 'W'},
            {'W', 'W', 'D', 'W', 'W'}
    };

    @Override
    public Character[][] getStringTemplate() {
        return sectorMap;
    }

    @Override
    public List<Integer> getCompatibility() {
        return Arrays.asList(1, 8);
    }
}
