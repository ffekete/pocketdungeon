package com.blacksoft.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.blacksoft.NewGameInitializer;
import com.blacksoft.build.UserAction;
import com.blacksoft.dungeon.actions.CleanIndicatorUpdater;
import com.blacksoft.dungeon.actions.CleanIndicatorsAction;
import com.blacksoft.screen.input.MapClickHandler;
import com.blacksoft.screen.input.MapMouseMovedHandler;
import com.blacksoft.screen.render.OrthogonalTiledMapRenderer;
import com.blacksoft.state.GameState;
import com.blacksoft.ui.TileMarker;

import static com.blacksoft.state.Config.SCREEN_HEIGHT;
import static com.blacksoft.state.Config.SCREEN_WIDTH;

public class BuilderScreen extends ScreenAdapter {


    private SpriteBatch spriteBatch;
    private TiledMapRenderer tiledMapRenderer;

    public BuilderScreen(SpriteBatch spriteBatch) {
        this.spriteBatch = spriteBatch;
    }

    @Override
    public void show() {

        GameState.orthographicCamera = new OrthographicCamera();
        GameState.viewport = new FitViewport(SCREEN_WIDTH / 2f, SCREEN_HEIGHT / 2f, GameState.orthographicCamera);
        GameState.viewport.apply(true);

        GameState.tileMarker = new TileMarker();
        GameState.tileMarker.setVisible(false);

        GameState.userAction = UserAction.Clean;

        GameState.stage = new Stage(GameState.viewport);
        GameState.stage.addActor(GameState.tileMarker);

        GameState.stage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {

                return MapClickHandler.touchDown((int) x, (int) y);
            }

            @Override
            public boolean mouseMoved(InputEvent event,
                                      float x,
                                      float y) {
                return MapMouseMovedHandler.mouseMoved(event, x, y);
            }

            @Override
            public boolean keyUp(InputEvent event,
                                 int keycode) {
                if (Input.Keys.ESCAPE == keycode) {
                    System.exit(0);
                }
                return true;
            }
        });

        Gdx.input.setInputProcessor(GameState.stage);

        GameState.orthographicCamera.translate(-20, -20);

        NewGameInitializer.init();
        this.tiledMapRenderer = new OrthogonalTiledMapRenderer(GameState.dungeon.tiledMap, spriteBatch);

        CleanIndicatorsAction.cleanAll(GameState.dungeon);
        CleanIndicatorUpdater.update(GameState.dungeon);

        // add ui elements
        GameState.stage.addActor(UIFactory.I.getStatusBar());
        GameState.stage.addActor(UIFactory.I.getFpsIndicator());
        GameState.stage.addActor(UIFactory.I.addMovingLabelShadow("BUILD PHASE"));
        GameState.stage.addActor(UIFactory.I.addMovingLabel("BUILD PHASE"));
        GameState.stage.addActor(UIFactory.I.getActionsGroup());

    }

    @Override
    public void render(float delta) {
        GameState.stage.act();

        super.render(delta);
        tiledMapRenderer.setView(GameState.orthographicCamera);
        tiledMapRenderer.render();

        GameState.stage.draw();
    }

    @Override
    public void resize(int width,
                       int height) {

        GameState.viewport.update(width, height, true);
        GameState.viewport.apply(true);
    }
}
