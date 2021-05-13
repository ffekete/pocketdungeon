package com.blacksoft.screen.action;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.blacksoft.user.actions.UserAction;
import com.blacksoft.dungeon.actions.build.BuildingPlacer;
import com.blacksoft.screen.UIFactory;
import com.blacksoft.state.GameState;

import static com.blacksoft.state.Config.TEXTURE_SIZE;

public class MovePlaceableActorToMouseAction extends Action {

    public MovePlaceableActorToMouseAction(Actor actor) {
        this.actor = actor;
    }

    private Label previousUpgradeIndicator = null;

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

                int mapX = (int) v.x / TEXTURE_SIZE;
                int mapY = (int) v.y / TEXTURE_SIZE;

                if (BuildingPlacer.canUpgrade(mapX, mapY)) {

                    if (previousUpgradeIndicator == null) {
                        previousUpgradeIndicator = UIFactory.I.getLabel14("^" + Integer.toString(GameState.dungeon.nodes[mapX][mapY].building.getUpgradeLevel() + 1));
                        GameState.uiStage.addActor(previousUpgradeIndicator);
                    }

                    previousUpgradeIndicator.setText("^" + Integer.toString(GameState.dungeon.nodes[mapX][mapY].building.getUpgradeLevel() + 1));

                    previousUpgradeIndicator.setVisible(true);
                    previousUpgradeIndicator.setPosition(v.x + 2, v.y);
                } else {
                    if (previousUpgradeIndicator != null) {
                        previousUpgradeIndicator.setVisible(false);
                    }
                }

            } else {
                imageButton.getImage().setColor(Color.RED);
                if (previousUpgradeIndicator != null) {
                    previousUpgradeIndicator.setVisible(false);
                }
            }


            return false;
        } else {
            if (previousUpgradeIndicator != null) {
                previousUpgradeIndicator.setVisible(false);
            }
        }

        return true;
    }


}
