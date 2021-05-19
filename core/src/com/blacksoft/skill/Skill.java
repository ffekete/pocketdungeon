package com.blacksoft.skill;

import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.blacksoft.creature.Creature;
import com.blacksoft.user.actions.UserAction;

import java.util.List;

public interface Skill {

    TriFunction<Creature, List<Creature>, List<Creature>, Action> getAction();

    UserAction getUserAction();

    Texture getIcon();

    Cursor getCursor();
}
