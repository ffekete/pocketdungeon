package com.blacksoft.dungeon.actions;


import com.badlogic.gdx.scenes.scene2d.Action;
import com.blacksoft.dungeon.objects.Door;
import com.blacksoft.state.GameState;

public class GateOpenCheckAction extends Action {

    private Door door;
    private int x;
    private int y;


    public GateOpenCheckAction(Door door,
                               int x,
                               int y) {
        this.door = door;
        this.x = x / 16;
        this.y = y / 16;
    }

    @Override
    public boolean act(float delta) {

        if(door.locked) {
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

        if (door.getState()) { // opened
            if (!areThereAnyNearby) {
                door.toggleState();
            }
        } else {
            if (areThereAnyNearby) {
                door.toggleState();
            }
        }
        //System.out.println("elapsed: " + (System.currentTimeMillis() - s));

        return false;
    }
}
