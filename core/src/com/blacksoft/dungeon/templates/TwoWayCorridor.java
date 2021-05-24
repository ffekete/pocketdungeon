package com.blacksoft.dungeon.templates;

public class TwoWayCorridor extends SectorTemplate {

    private Character[][] sectorMap = new Character[][]{
            {'W', 'W', '.', 'W', 'W'},
            {'W', 'W', '.', 'W', 'W'},
            {'.', '.', '.', 'W', 'W'},
            {'W', 'W', 'W', 'W', 'W'},
            {'W', 'W', 'W', 'W', 'W'}
    };

    @Override
    public Character[][] getStringTemplate() {
        return sectorMap;
    }

    @Override
    public int getCompatibility() {
        return 15;
    }
}
