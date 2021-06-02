package com.blacksoft.skill;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.blacksoft.creature.Creature;
import com.blacksoft.creature.action.DefendSelfAction;
import com.blacksoft.state.UIState;
import com.blacksoft.ui.AnimatedImage;
import com.blacksoft.user.actions.UserAction;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MeleeDefense implements Skill {

    public static final Texture icon;

    static {
        icon = new Texture(Gdx.files.internal("skill/DefenseIcon.png"));
    }

    @Override
    public TriFunction<Creature, List<Creature>, List<Creature>, Action> getAction() {
        return ((creature, allies, enemies) -> new DefendSelfAction(creature));
    }

    @Override
    public Texture getIcon() {
        return icon;
    }

    @Override
    public UserAction getUserAction() {
        return UserAction.Disabled;
    }

    @Override
    public Cursor getCursor() {
        return UIState.defaultCursor;
    }

    @Override
    public Table getSubmenu() {
        return null;
    }

    @Override
    public Map<Skill, AnimatedImage> getSkillsAndIcons() {
        return Collections.emptyMap();
    }
}
