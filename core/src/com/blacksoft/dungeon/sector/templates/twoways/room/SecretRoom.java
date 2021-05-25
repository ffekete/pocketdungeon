package com.blacksoft.dungeon.sector.templates.twoways.room;

import com.blacksoft.dungeon.sector.templates.SectorTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SecretRoom extends SectorTemplate {

    private Character[][] sectorMap = new Character[][]{
            {'W', 'W', 'W', 'W', 'W'},
            {'W', '.', '.', '.', 'W'},
            {'W', '.', '.', '.', 'W'},
            {'W', '.', '.', '.', 'W'},
            {'W', 'W', 'W', 'W', 'W'}
    };

    @Override
    public Character[][] getStringTemplate() {
        return sectorMap;
    }

    @Override
    public List<Integer> getCompatibility() {
        return Collections.emptyList();
    }
}
