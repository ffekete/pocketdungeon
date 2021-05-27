package com.blacksoft.dungeon.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.objects.TextureMapObject;

public abstract class AbstractMapObject extends TextureMapObject implements GameObject {

    @Override
    public TextureRegion getTextureRegion() {
        return super.getTextureRegion();
    }
}