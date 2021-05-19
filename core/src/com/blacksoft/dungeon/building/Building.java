package com.blacksoft.dungeon.building;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blacksoft.dungeon.Tile;

public interface Building {

    void place(int x,
               int y);

    void destroy();

    Tile getTile();

    boolean getState();

    void toggleState();

    TextureRegion getTextureRegion();

    int getX();

    int getY();
}
