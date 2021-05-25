package com.blacksoft.dungeon.sector.templates.twoways.corridor.twoway;

import com.blacksoft.dungeon.sector.templates.SectorTemplate;

import java.util.Arrays;
import java.util.List;

public class TwoWayCorridorC extends SectorTemplate {

    private Character[][] sectorMap = new Character[][]{
            {'W', 'W', '.', 'W', 'W'},
            {'W', 'W', '.', 'W', 'W'},
            {'W', 'W', '.', 'W', 'W'},
            {'W', 'W', '.', 'W', 'W'},
            {'W', 'W', '.', 'W', 'W'}
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
