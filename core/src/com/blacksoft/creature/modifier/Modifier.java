package com.blacksoft.creature.modifier;

import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public interface Modifier {

    void apply(SequenceAction sequenceAction);

    int getDuration();

    int getAmount();

    void finish(SequenceAction parallelAction);

    void start(SequenceAction parallelAction);


    Modifier merge(Modifier modifier);
}
