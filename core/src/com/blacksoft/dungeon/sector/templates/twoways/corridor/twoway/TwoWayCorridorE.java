package com.blacksoft.dungeon.sector.templates.twoways.corridor.twoway;

import com.blacksoft.dungeon.sector.templates.SectorTemplate;

import java.util.Arrays;
import java.util.List;

public class TwoWayCorridorE extends SectorTemplate {

    private Character[][] sectorMap = new Character[][]{
            {'W', 'W', 'W', 'W', 'W'},
            {'W', 'W', 'M', 'T', 'W'},
            {'W', 'W', '.', '.', '.'},
            {'W', 'W', 'D', 'W', 'W'},
            {'W', 'W', '.', 'W', 'W'}
    };

    @Override
    public Character[][] getStringTemplate() {
        return sectorMap;
    }

    @Override
    public List<Integer> getCompatibility() {
        return Arrays.asList(4, 8);
    }
}
