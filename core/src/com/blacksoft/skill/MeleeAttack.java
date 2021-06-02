package com.blacksoft.skill;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.blacksoft.creature.Creature;
import com.blacksoft.skill.action.CancellableAction;
import com.blacksoft.skill.action.DamageSingleTargetAction;
import com.blacksoft.state.UIState;
import com.blacksoft.ui.AnimatedImage;
import com.blacksoft.user.actions.UserAction;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MeleeAttack implements Skill {

    public static final Texture icon;

    static {
        icon = new Texture(Gdx.files.internal("skill/AttackIcon.png"));
    }

    private Creature initiator;

    public MeleeAttack(Creature initiator) {
        this.initiator = initiator;
    }

    @Override
    public TriFunction<Creature, List<Creature>, List<Creature>, Action> getAction() {
        return (initiator, creatures, heroes) -> CancellableAction.of(new DamageSingleTargetAction(initiator.getMeleeDamage()));
    }

    @Override
    public UserAction getUserAction() {
        return UserAction.SelectSingleTarget;
    }

    @Override
    public Texture getIcon() {
        return icon;
    }

    @Override
    public Cursor getCursor() {
        return UIState.attackCursor;
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
