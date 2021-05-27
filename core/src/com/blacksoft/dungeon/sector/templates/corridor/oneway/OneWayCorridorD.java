package com.blacksoft.dungeon.sector.templates.corridor.oneway;

import com.blacksoft.dungeon.sector.templates.SectorTemplate;

import java.util.Arrays;
import java.util.List;

public class OneWayCorridorD extends SectorTemplate {

    private Character[][] sectorMap = new Character[][]{
            {'W', 'W', 'M', 'W', 'W'},
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
        return Arrays.asList(8);
    }
}
