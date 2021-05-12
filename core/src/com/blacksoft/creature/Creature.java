package com.blacksoft.creature;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.blacksoft.state.GameState;

public abstract class Creature extends Actor {

    public Vector2 previousNode = null;
    public Vector2 targetNode = null;
    public boolean finishedAllActions = true;

    public float morale = 100f;
    public float hp = 10f;

    public SequenceAction sequenceActions = new SequenceAction();

    public Creature() {
        addAction(sequenceActions);
    }

    public void die() {
        GameState.creatures.remove(this);
        GameState.stage.getActors().removeIndex(GameState.stage.getActors().indexOf(this, true));
    }

    public abstract float getSpeed();

    public abstract int getSalaryRequest();

    public void reduceMorale(float amount) {
        morale -= amount;

        if(morale < -50f) {
            // leave
            this.die();
        }
    }

    public void reduceHp(int amount) {
        this.hp -= amount;
        if(this.hp < 0) {
            die();
        }
    }

    public float getHp() {
        return hp;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        sequenceActions.act(delta);
    }
}
