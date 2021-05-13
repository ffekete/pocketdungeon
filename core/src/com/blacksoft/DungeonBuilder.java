package com.blacksoft;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.blacksoft.screen.BuilderScreen;
import com.blacksoft.state.GameState;

public class DungeonBuilder extends ApplicationAdapter {
    SpriteBatch batch;

    ScreenAdapter builderScreen;

    @Override
    public void create() {
        batch = new SpriteBatch();
        builderScreen = new BuilderScreen(batch);

        Pixmap pm = new Pixmap(Gdx.files.internal("ui/Cursor.png"));
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 0, 0));
        pm.dispose();

        builderScreen.show();
    }

    @Override
    public void render() {
        GameState.orthographicCamera.update();
        batch.setProjectionMatrix(GameState.orthographicCamera.combined);
        ScreenUtils.clear(0, 0, 0, 1);
        builderScreen.render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public void resize(int width,
                       int height) {
        GameState.viewport.update(width, height, false);
        GameState.uiViewport.update(width, height, false);
        GameState.orthographicCamera.translate(10, 0);
        GameState.orthographicUICamera.translate(-10, -20);
    }
}
