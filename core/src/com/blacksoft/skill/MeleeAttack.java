package com.blacksoft.skill;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blacksoft.creature.Creature;

public class MeleeAttack implements Skill<Creature> {

    private static final Texture icon;

    static {
        icon = new Texture(Gdx.files.internal("skill/AttackIcon.png"));
    }

    @Override
    public void select(Creature target) {

    }

    @Override
    public void act() {

    }

    @Override
    public Texture getIcon() {
        return icon;
    }
}
