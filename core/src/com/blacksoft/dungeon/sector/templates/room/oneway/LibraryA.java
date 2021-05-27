package com.blacksoft.dungeon.sector.templates.room.oneway;

import com.blacksoft.dungeon.sector.templates.SectorTemplate;

import java.util.Arrays;
import java.util.List;

public class LibraryA extends SectorTemplate {

    private Character[][] sectorMap = new Character[][]{
            {'W', 'T', 'D', 'T', 'W'},
            {'W', 'b', '.', 'b', 'W'},
            {'W', '.', '#', '.', 'W'},
            {'W', 't', '.', '.', 'W'},
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
