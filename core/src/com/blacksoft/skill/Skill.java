package com.blacksoft.skill;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public interface Skill<T> {

    void select(T target);

    void act();

    Texture getIcon();

}
