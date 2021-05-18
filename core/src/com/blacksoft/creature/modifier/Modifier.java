package com.blacksoft.creature.modifier;

import com.blacksoft.creature.Creature;

public interface Modifier {

    void apply();

    int getDuration();

    void finish();

    void start();

}
