package com.blacksoft.skill;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.blacksoft.creature.Creature;
import com.blacksoft.creature.action.DefendSelfAction;
import com.blacksoft.state.GameState;
import com.blacksoft.user.actions.UserAction;

import java.util.List;

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
}
