package com.blacksoft.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class TileMarker extends Actor {

    private final Texture texture;

    public TileMarker() {
        this.texture = new Texture(Gdx.files.internal("ui/Marker.png"));
    }

    @Override
    public void draw(Batch batch,
                     float parentAlpha) {
        if (isVisible()) {
            batch.draw(texture, getX(), getY());
        }
    }
}
