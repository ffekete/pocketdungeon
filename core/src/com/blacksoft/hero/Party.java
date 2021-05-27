package com.blacksoft.hero;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.blacksoft.creature.Creature;
import com.blacksoft.creature.State;
import com.blacksoft.dungeon.Node;
import com.blacksoft.state.Config;

import java.util.ArrayList;
import java.util.List;

public class Party extends Actor {

    public List<Creature> heroes = new ArrayList<>();

    public boolean finishedAllActions = true;

    public boolean explored[][] = new boolean[Config.MAP_WIDTH][Config.MAP_HEIGHT];

    public SequenceAction sequenceActions = new SequenceAction();

    public Vector2 previousNode;
    public Vector2 targetNode;

    public State state = State.Idle;

    @Override
    public void act(float delta) {
        super.act(delta);
        heroes.stream()
                .forEach(hero -> {
                    hero.setX(getX());
                    hero.setY(getY());
                });
    }

    @Override
    public void draw(Batch batch,
                     float parentAlpha) {
        if(!heroes.isEmpty()) {
            heroes.get(0).draw(batch, parentAlpha);
        }
    }

    public float getSpeed() {
        return heroes.stream()
                .map(Creature::getSpeed)
                .max(Float::compareTo)
                .orElse(0f);
    }
}
