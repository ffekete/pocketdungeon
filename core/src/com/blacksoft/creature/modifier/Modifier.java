package com.blacksoft.creature.modifier;

import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;

public interface Modifier {

    void apply(ParallelAction sequenceAction);

    int getDuration();

    void finish(ParallelAction parallelAction);

    void start(ParallelAction parallelAction);

}
