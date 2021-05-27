package com.blacksoft.dungeon.sector.templates.corridor.threeway;

import com.blacksoft.dungeon.sector.templates.SectorTemplate;

import java.util.Arrays;
import java.util.List;

public class ThreeWayCorridorC extends SectorTemplate {

    private Character[][] sectorMap = new Character[][]{
            {'W', 'W', 'W', 'W', 'W'},
            {'W', 'T', 'M', 'T', 'W'},
            {'.', '.', '.', '.', '.'},
            {'W', 'W', 'D', 'W', 'W'},
            {'W', 'W', '.', 'W', 'W'}
    };

    @Override
    public Character[][] getStringTemplate() {
        return sectorMap;
    }

    @Override
    public List<Integer> getCompatibility() {
        return Arrays.asList(1, 4, 8);
    }
}
