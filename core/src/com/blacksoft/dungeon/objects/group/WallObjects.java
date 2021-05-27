package com.blacksoft.dungeon.objects.group;

import com.blacksoft.dungeon.objects.*;
import com.blacksoft.dungeon.objects.wall.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class WallObjects {
    public static final WallObjects I = new WallObjects();

    private final List<Supplier<AbstractMapObject>> objects = new ArrayList<>();

    public WallObjects() {
        objects.add(() -> new Torch());
        objects.add(() -> new Torch());
        objects.add(() -> new Torch());
        objects.add(() -> new Torch());

        objects.add(() -> new ArmsDecoration());

        objects.add(() -> new Moss());

        objects.add(() -> new Banner01());
        objects.add(() -> new Banner02());
    }

    public AbstractMapObject pickOneRandom() {
        return objects.get(new Random().nextInt(objects.size())).get();
    }
}
