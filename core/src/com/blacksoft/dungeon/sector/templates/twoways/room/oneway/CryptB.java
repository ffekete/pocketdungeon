package com.blacksoft.dungeon.sector.templates.twoways.room.oneway;

import com.blacksoft.dungeon.sector.templates.SectorTemplate;

import java.util.Arrays;
import java.util.List;

public class CryptB extends SectorTemplate {

    private Character[][] sectorMap = new Character[][]{
            {'W', 'T', 'M', 'T', 'W'},
            {'W', 'g', '+', 'g', 'W'},
            {'W', 'g', '.', 'g', 'W'},
            {'W', 'g', '.', 'g', 'W'},
            {'W', 'W', 'D', 'W', 'W'}
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
