package com.blacksoft.dungeon.actions;


import com.badlogic.gdx.scenes.scene2d.Action;
import com.blacksoft.dungeon.building.Gate;
import com.blacksoft.state.GameState;

public class GateOpenCheckAction extends Action {

    private Gate gate;
    private int x;
    private int y;


    public GateOpenCheckAction(Gate gate,
                               int x,
                               int y) {
        this.gate = gate;
        this.x = x / 16;
        this.y = y / 16;
    }

    @Override
    public boolean act(float delta) {

        if(gate.locked) {
            return false;
        }

        long s = System.currentTimeMillis();
        boolean areThereAnyNearby = GameState.creatures.stream()
                .anyMatch(creature -> ((int) creature.getX() / 16 - 1 == x && (int) creature.getY() / 16 == y) ||
                        ((int) creature.getX() / 16 + 1 == x && (int) creature.getY() / 16 == y) ||
                        ((int) creature.getX() / 16 == x && (int) creature.getY() / 16 - 1 == y) ||
                        ((int) creature.getX() / 16 == x && (int) creature.getY() / 16 + 1 == y) ||
                        ((int) creature.getX() / 16 == x && (int) creature.getY() / 16 == y)
                );

        if (gate.getState()) { // opened
            if (!areThereAnyNearby) {
                gate.toggleState();
            }
        } else {
            if (areThereAnyNearby) {
                gate.toggleState();
            }
        }
        //System.out.println("elapsed: " + (System.currentTimeMillis() - s));

        return false;
    }
}
