package com.blacksoft.battle;

import com.blacksoft.creature.Creature;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BattleSequence {

    public static List<Creature> creatures = new ArrayList<>();

    private static int index = 0;

    public static void add(Creature creature) {
        creatures.add(creature);
        creatures.sort(new Comparator<Creature>() {
            @Override
            public int compare(Creature o1,
                               Creature o2) {
                return -1 * Float.compare(o1.getSpeed(), o2.getSpeed());
            }
        });
    }

    public static Creature getNext() {
        if(index >= creatures.size()) {
            index = 0;
        }

        while(index < creatures.size() && creatures.get(index).hp <= 0) {
            index++;
        }

        if(index < creatures.size()) {
            return creatures.get(index++);
        }

        index = 0;
        while(index < creatures.size() && creatures.get(index).hp <= 0) {
            index++;
        }

        if(index < creatures.size()) {
            return creatures.get(index++);
        }
        return null;
    }

}
