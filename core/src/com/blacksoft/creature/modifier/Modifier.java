package com.blacksoft.creature.modifier;

import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public interface Modifier {

    void apply(SequenceAction sequenceAction);

    int getDuration();

    void finish(SequenceAction parallelAction);

    void start(SequenceAction parallelAction);

}
