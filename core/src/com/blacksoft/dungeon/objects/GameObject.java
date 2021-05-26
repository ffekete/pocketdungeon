package com.blacksoft.dungeon.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blacksoft.dungeon.Tile;

public interface GameObject {

    void place(int x,
               int y);

    void destroy();

    boolean getState();

    void toggleState();

    TextureRegion getTextureRegion();
}
