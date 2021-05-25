package com.blacksoft.dungeon.sector.templates.twoways.corridor.oneway;

import com.blacksoft.dungeon.sector.templates.SectorTemplate;

import java.util.Arrays;
import java.util.List;

public class OneWayCorridorB extends SectorTemplate {

    private Character[][] sectorMap = new Character[][]{
            {'W', 'W', '.', 'W', 'W'},
            {'W', 'W', '.', 'W', 'W'},
            {'W', 'W', '.', 'W', 'W'},
            {'W', 'W', '.', 'W', 'W'},
            {'W', 'W', 'W', 'W', 'W'}
    };

    @Override
    public Character[][] getStringTemplate() {
        return sectorMap;
    }

    @Override
    public List<Integer> getCompatibility() {
        return Arrays.asList(2);
    }
}
