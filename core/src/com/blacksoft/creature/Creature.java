package com.blacksoft.creature;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.blacksoft.creature.modifier.Modifier;
import com.blacksoft.skill.MeleeAttack;
import com.blacksoft.skill.MeleeDefense;
import com.blacksoft.skill.Poison;
import com.blacksoft.skill.Skill;
import com.blacksoft.state.GameState;
import com.blacksoft.state.UIState;

import java.util.ArrayList;
import java.util.List;

public abstract class Creature extends Actor {

    public Vector2 previousNode = null;
    public Vector2 targetNode = null;
    public boolean finishedAllActions = true;
    public Direction direction;

    public int hp;
    public int mp;

    public State state = State.Idle;

    // modifiers
    private List<Modifier> modifiers = new ArrayList<>();
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
        UIState.creatureListTable.removeActor(GameState.creatureListEntriesOnSidePanel.get(this));
    }

    public abstract float getSpeed();

    public void increaseHp(int amount) {
        if (this.hp > 0) {
            this.hp += amount;
        }
    }

    public void reduceHp(int amount) {
        if (this.hp > 0) {
            this.hp -= amount;
            if (this.hp <= 0) {
                die();
            }
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

    public void applyModifiers(SequenceAction sequenceAction) {
        List<Modifier> removeThese = new ArrayList<>();

        // reset mod values
        tempDefenceModifier = 0;

        // apply new ones
        for (Modifier modifier : modifiers) {

            modifier.apply(sequenceAction);

            if (modifier.getDuration() <= 0) {
                modifier.finish(sequenceAction);
                removeThese.add(modifier);
            }
        }

        modifiers.removeAll(removeThese);
    }

    public void addModifier(Modifier modifier) {

        for (Modifier aModifier : modifiers) {
            if (aModifier.merge(modifier) == null) {
                return;
            }
        }
        modifiers.add(modifier);
    }
}
