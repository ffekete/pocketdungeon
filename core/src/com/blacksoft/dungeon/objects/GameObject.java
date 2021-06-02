package com.blacksoft.dungeon.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blacksoft.dungeon.Tile;
import com.blacksoft.hero.Party;

public interface GameObject {

    void place(int x,
               int y);

    void destroy();

    boolean getState();

    void interact(Party party);

    TextureRegion getTextureRegion();
}
