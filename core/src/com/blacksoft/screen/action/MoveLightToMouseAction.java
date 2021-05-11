package com.blacksoft.screen.action;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;

public class MoveLightToMouseAction extends Action {

    @Override
    public boolean act(float delta) {


        Vector3 v = GameState.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0f));
        if(v.x >= 0 && v.x < Config.MAP_WIDTH * 16 && v.y >= 0 && v.y < Config.MAP_HEIGHT * 16) {
            GameState.mouseLightSource.setActive(true);
            GameState.mouseLightSource.setPosition(v.x, v.y);
        } else {
            GameState.mouseLightSource.setActive(false);
        }

        return false;
    }


}
