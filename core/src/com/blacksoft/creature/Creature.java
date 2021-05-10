package com.blacksoft.creature;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.blacksoft.state.GameState;

public abstract class Creature extends Actor {

    public Vector2 previousNode = null;
    public Vector2 targetNode = null;
    public boolean finishedMoving = true;

    public void die() {
        GameState.creatures.remove(this);
        GameState.stage.getActors().removeIndex(GameState.stage.getActors().indexOf(this, true));
    }

}
