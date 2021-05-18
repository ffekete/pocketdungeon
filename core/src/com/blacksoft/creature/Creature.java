package com.blacksoft.creature;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.blacksoft.creature.modifier.Modifier;
import com.blacksoft.skill.MeleeAttack;
import com.blacksoft.skill.MeleeDefense;
import com.blacksoft.skill.Skill;
import com.blacksoft.state.GameState;
import com.blacksoft.state.UIState;

import java.util.ArrayList;
import java.util.List;

public abstract class Creature extends Actor {

    public Vector2 previousNode = null;
    public Vector2 targetNode = null;
    public boolean finishedAllActions = true;

    public float morale = 100f;
    public int hp;
    public int mp;

    // modifiers
    public List<Modifier> modifiers = new ArrayList<>();
    public int tempDefenceModifier = 0;
    // modifiers end

    public int level = 1;

    public List<Skill> skills;

    public SequenceAction sequenceActions = new SequenceAction();

    public Creature() {
        addAction(sequenceActions);
        hp = getMaxHp();
        this.skills = new ArrayList<>();
        this.skills.add(new MeleeAttack(this));
        this.skills.add(new MeleeDefense());
    }

    public void die() {
        GameState.creatures.remove(this);
        GameState.stage.getActors().removeIndex(GameState.stage.getActors().indexOf(this, true));
        UIState.creatureListTable.removeActor(GameState.creatureListEntries.get(this));
    }

    public abstract float getSpeed();

    public abstract int getSalaryRequest();

    public void reduceMorale(float amount) {
        morale -= amount;

        if (morale < -50f) {
            // leave
            this.die();
        }
    }

    public void reduceHp(int amount) {
        this.hp -= amount;
        if (this.hp <= 0) {
            die();
        }
    }

    public int getHp() {
        return hp;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        sequenceActions.act(delta);
    }

    public abstract int getMaxHp();

    public abstract int getMaxMp();

    public abstract Animation<TextureRegion> getAnimation();

    public abstract int getMeleeDamage();

    public abstract int getMeleeDefence();

    public void applyModifiers() {
        List<Modifier> removeThese = new ArrayList<>();

        // reset mod values
        tempDefenceModifier = 0;

        // apply new ones
        for (Modifier modifier : modifiers) {
            modifier.apply();
            if (modifier.getDuration() <= 0) {
                modifier.finish();
                removeThese.add(modifier);
            }
        }

        modifiers.removeAll(removeThese);
    }
}
