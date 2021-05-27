package com.blacksoft.dungeon.objects.group;

import com.blacksoft.dungeon.objects.AbstractMapObject;
import com.blacksoft.dungeon.objects.floor.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class Carpets {

    public static final Carpets I = new Carpets();

    public final List<Supplier<AbstractMapObject>>  objects = new ArrayList<>();

    public Carpets() {
        objects.add(() -> new Carpet01());
    }

    public AbstractMapObject pickOneRandom() {
        return objects.get(new Random().nextInt(objects.size())).get();
    }
}
