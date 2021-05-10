package com.blacksoft.dungeon.actions;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class AbstractAction extends Actor {

    public abstract int getPriority();

    public abstract ActionLevel getActionLevel();

    public abstract TextureRegion getTexture();

    public abstract String getTitle();

    public abstract String getDescription();

    public abstract void execute();

    public abstract int getProgressAmount();
}
