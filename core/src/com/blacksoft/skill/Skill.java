package com.blacksoft.skill;

import com.badlogic.gdx.graphics.Texture;

public interface Skill<T> {

    void select(T target);

    void act();

    Texture getIcon();

}
