package com.blacksoft.dungeon.sector.templates;

import java.util.Arrays;
import java.util.List;

public class FourWayCorridor extends SectorTemplate {

    private Character[][] sectorMap = new Character[][]{
            {'W', 'W', 'D', 'W', 'W'},
            {'W', 'T', '.', 'T', 'W'},
            {'.', '.', '.', '.', '.'},
            {'W', 'W', '.', 'W', 'W'},
            {'W', 'W', 'D', 'W', 'W'}
    };

    @Override
    public Character[][] getStringTemplate() {
        return sectorMap;
    }

    @Override
    public List<Integer> getCompatibility() {
        return Arrays.asList(1, 2, 4, 8);
    }
}
