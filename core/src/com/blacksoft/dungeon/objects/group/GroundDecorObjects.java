package com.blacksoft.dungeon.objects.group;

import com.blacksoft.dungeon.objects.AbstractMapObject;
import com.blacksoft.dungeon.objects.floor.Carpet01;
import com.blacksoft.dungeon.objects.floor.Gutter01;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class GroundDecorObjects {

    public static final GroundDecorObjects I = new GroundDecorObjects();

    private final List<Supplier<AbstractMapObject>>  objects = new ArrayList<>();

    public GroundDecorObjects() {
        objects.addAll(Carpets.I.objects);
        objects.add(() -> new Gutter01());
    }

    public AbstractMapObject pickOneRandom() {
        return objects.get(new Random().nextInt(objects.size())).get();
    }
}
