package com.blacksoft.skill;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.blacksoft.creature.Creature;
import com.blacksoft.creature.action.DamageSingleTargetAction;
import com.blacksoft.user.actions.UserAction;

import java.util.List;

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
        return (initiator, creatures, heroes) -> new DamageSingleTargetAction(initiator.getMeleeDamage());
    }

    @Override
    public UserAction getUserAction() {
        return UserAction.SelectSingleTarget;
    }

    @Override
    public Texture getIcon() {
        return icon;
    }
}
