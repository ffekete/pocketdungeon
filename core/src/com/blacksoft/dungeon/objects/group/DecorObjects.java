package com.blacksoft.dungeon.objects.group;

import com.blacksoft.dungeon.objects.*;
import com.blacksoft.dungeon.objects.floor.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class DecorObjects {

    public static final DecorObjects I = new DecorObjects();

    private final List<Supplier<AbstractMapObject>>  objects = new ArrayList<>();

    public DecorObjects() {
        objects.add(() -> new BookShelf());
        objects.add(() -> new BookShelf());
        objects.add(() -> new BookShelf());

        objects.add(() -> new Graveyard());

        objects.add(() -> new Table());

        objects.add(() -> new TreasureChest());
        objects.add(() -> new TreasureChest());
        objects.add(() -> new TreasureChest());
        objects.add(() -> new TreasureChest());

        objects.add(() -> new VampireCoffin());
    }

    public AbstractMapObject pickOneRandom() {
        return objects.get(new Random().nextInt(objects.size())).get();
    }
}
