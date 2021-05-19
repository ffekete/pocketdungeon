package com.blacksoft.screen.action;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.blacksoft.dungeon.actions.build.BuildingPlacer;
import com.blacksoft.state.GameState;
import com.blacksoft.user.actions.UserAction;

public class MovePlaceableActorToMouseAction extends Action {

    public MovePlaceableActorToMouseAction(Actor actor) {
        this.actor = actor;
    }

    @Override
    public boolean act(float delta) {

        if(GameState.selectedAction == null || GameState.selectedActionImage == null) {
            return true;
        }

        if (GameState.userAction == UserAction.Place) {
            Vector3 v = GameState.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0f));
            actor.setPosition(v.x, v.y);

            ImageButton imageButton = (ImageButton) actor;

            if (BuildingPlacer.canPlace((int) v.x, (int) v.y, GameState.selectedAction.getActionResultClass())) {
                imageButton.getImage().setColor(Color.GREEN);
            } else {
                imageButton.getImage().setColor(Color.RED);
            }

            return false;
        }

        return true;
    }


}
