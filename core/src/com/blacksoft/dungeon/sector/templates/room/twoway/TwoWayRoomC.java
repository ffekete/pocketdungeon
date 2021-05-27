package com.blacksoft.dungeon.sector.templates.room.twoway;

import com.blacksoft.dungeon.sector.templates.SectorTemplate;

import java.util.Arrays;
import java.util.List;

public class TwoWayRoomC extends SectorTemplate {

    private Character[][] sectorMap = new Character[][]{
            {'W', 'T', 'D', 'T', 'W'},
            {'W', '@', '.', '@', 'W'},
            {'W', '.', ',', '.', 'W'},
            {'W', '.', '.', '.', 'W'},
            {'W', 'W', 'D', 'W', 'W'}
    };

    @Override
    public Character[][] getStringTemplate() {
        return sectorMap;
    }

    @Override
    public List<Integer> getCompatibility() {
        return Arrays.asList(2, 8);
    }
}
