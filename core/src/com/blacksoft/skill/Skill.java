package com.blacksoft.skill;

import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.blacksoft.creature.Creature;
import com.blacksoft.ui.AnimatedImage;
import com.blacksoft.user.actions.UserAction;

import java.util.List;
import java.util.Map;

public interface Skill {

    TriFunction<Creature, List<Creature>, List<Creature>, Action> getAction();

    UserAction getUserAction();

    Texture getIcon();

    Cursor getCursor();

    Table getSubmenu();

    Map<Skill, AnimatedImage> getSkillsAndIcons();
}
