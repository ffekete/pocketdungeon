package com.blacksoft.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.util.function.Supplier;

public class DynamicLabel extends Label {

    private Supplier<String> textUpdater;

    public DynamicLabel(LabelStyle style,
                        Supplier<String> textUpdater) {
        super(textUpdater.get(), style);
        this.textUpdater = textUpdater;
    }

    @Override
    public void draw(Batch batch,
                     float parentAlpha) {
        if(!getText().toString().equals(textUpdater.get())) {
            setText(textUpdater.get());
        }

        super.draw(batch, parentAlpha);
    }
}
