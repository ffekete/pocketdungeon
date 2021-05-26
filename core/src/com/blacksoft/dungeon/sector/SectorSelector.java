package com.blacksoft.dungeon.sector;

import com.blacksoft.dungeon.sector.templates.FourWayCorridor;
import com.blacksoft.dungeon.sector.templates.SectorTemplate;
import com.blacksoft.dungeon.sector.templates.twoways.corridor.oneway.OneWayCorridorA;
import com.blacksoft.dungeon.sector.templates.twoways.corridor.oneway.OneWayCorridorB;
import com.blacksoft.dungeon.sector.templates.twoways.corridor.oneway.OneWayCorridorC;
import com.blacksoft.dungeon.sector.templates.twoways.corridor.oneway.OneWayCorridorD;
import com.blacksoft.dungeon.sector.templates.twoways.corridor.threeway.ThreeWayCorridorA;
import com.blacksoft.dungeon.sector.templates.twoways.corridor.threeway.ThreeWayCorridorB;
import com.blacksoft.dungeon.sector.templates.twoways.corridor.threeway.ThreeWayCorridorC;
import com.blacksoft.dungeon.sector.templates.twoways.corridor.threeway.ThreeWayCorridorD;
import com.blacksoft.dungeon.sector.templates.twoways.corridor.twoway.*;
import com.blacksoft.dungeon.sector.templates.twoways.room.SecretRoom;
import com.blacksoft.dungeon.sector.templates.twoways.room.oneway.OneWayRoomA;
import com.blacksoft.dungeon.sector.templates.twoways.room.oneway.OneWayRoomB;
import com.blacksoft.dungeon.sector.templates.twoways.room.oneway.OneWayRoomC;
import com.blacksoft.dungeon.sector.templates.twoways.room.oneway.OneWayRoomD;
import com.blacksoft.dungeon.sector.templates.twoways.room.threeway.ThreeWayRoomA;
import com.blacksoft.dungeon.sector.templates.twoways.room.threeway.ThreeWayRoomB;
import com.blacksoft.dungeon.sector.templates.twoways.room.threeway.ThreeWayRoomC;
import com.blacksoft.dungeon.sector.templates.twoways.room.threeway.ThreeWayRoomD;
import com.blacksoft.dungeon.sector.templates.twoways.room.twoway.*;
import com.blacksoft.state.GameState;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

import static com.blacksoft.state.Config.*;

public class SectorSelector {

    private List<SectorTemplate> sectors = new ArrayList<>();

    public SectorSelector() {
        sectors.add(new TwoWayCorridorA());
        sectors.add(new TwoWayCorridorB());
        sectors.add(new TwoWayCorridorC());
        sectors.add(new TwoWayCorridorD());
        sectors.add(new TwoWayCorridorE());

        sectors.add(new TwoWayRoomA());
        sectors.add(new TwoWayRoomB());
        sectors.add(new TwoWayRoomC());
        sectors.add(new TwoWayRoomD());
        sectors.add(new TwoWayRoomE());

        sectors.add(new ThreeWayCorridorA());
        sectors.add(new ThreeWayCorridorB());
        sectors.add(new ThreeWayCorridorC());
        sectors.add(new ThreeWayCorridorD());

        sectors.add(new ThreeWayRoomA());
        sectors.add(new ThreeWayRoomB());
        sectors.add(new ThreeWayRoomC());
        sectors.add(new ThreeWayRoomD());

        sectors.add(new OneWayCorridorA());
        sectors.add(new OneWayCorridorB());
        sectors.add(new OneWayCorridorC());
        sectors.add(new OneWayCorridorD());

        sectors.add(new OneWayRoomA());
        sectors.add(new OneWayRoomB());
        sectors.add(new OneWayRoomC());
        sectors.add(new OneWayRoomD());

        sectors.add(new FourWayCorridor());
    }

    public SectorTemplate findOne(int sx, int sy) {

        List<Integer> needed = new ArrayList<>();
        List<Integer> excluded = new ArrayList<>();

        int xOffset = 0;
        int yOffset = 0;

        if (sy == MAP_HEIGHT / SECTOR_SIZE - 1) {
            excluded.add(2);
        }

        if (sx == MAP_WIDTH / SECTOR_SIZE - 1) {
            excluded.add(4);
        }

        if (sx > 0) {
            if (GameState.dungeon.nodes[(sx - 1) * SECTOR_SIZE + xOffset][sy * SECTOR_SIZE + yOffset].compatibility.contains(4)) {
                needed.add(1);
            }
        } else {
            excluded.add(1);
        }

        if (sy > 0) {
            if (GameState.dungeon.nodes[sx * SECTOR_SIZE + xOffset][(sy - 1) * SECTOR_SIZE + yOffset].compatibility.contains(2)) {
                needed.add(8);
            }
        } else {
            excluded.add(8);
        }

        int whatToAdd = new Random().nextInt(3);

        if (needed.isEmpty()) {
            if (whatToAdd == 0 || whatToAdd == 1)
                needed.add(2);
            if (whatToAdd == 0 || whatToAdd == 2)
                needed.add(4);
        }

        if(needed.isEmpty()) {
            if(excluded.contains(2)) {
                needed.add(4);
            }

            if(excluded.contains(4)) {
                needed.add(2);
            }
        }

        if(needed.size() == 1) {

            List<Integer> toAdd = new ArrayList<>();
            toAdd.add(1);
            toAdd.add(2);
            toAdd.add(4);
            toAdd.add(8);

            toAdd.removeAll(excluded);
            int random = new Random().nextInt(toAdd.size() + 1);

            if(random == 0) {
                needed.addAll(toAdd);
            } else {
                for (int i = 1; i < toAdd.size(); i++) {
                    needed.add(toAdd.get(i));
                }
            }
        }


        List<SectorTemplate> templates = sectors.stream()
                .filter(sectorTemplate -> sectorTemplate.getCompatibility().containsAll(needed))
                .filter(sectorTemplate -> !containsAny(sectorTemplate.getCompatibility(), excluded))
                .collect(Collectors.toList());

        if(templates.isEmpty()) {
            templates.add(new SecretRoom());
            System.out.println("Kaka van " + sx + " " + sy + " " + needed + " " + excluded);
        }

        return templates.get(new Random().nextInt(templates.size()));
    }

    private boolean containsAny(Collection<Integer> c1, Collection<Integer> c2) {
        return c2.stream().anyMatch(c1::contains);
    }

}
