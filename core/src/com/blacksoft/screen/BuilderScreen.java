package com.blacksoft.screen;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.blacksoft.NewGameInitializer;
import com.blacksoft.dungeon.actions.ui.CleanIndicatorUpdater;
import com.blacksoft.dungeon.actions.ui.CleanIndicatorsAction;
import com.blacksoft.screen.action.MoveLightToMouseAction;
import com.blacksoft.screen.render.OrthogonalTiledMapRenderer;
import com.blacksoft.state.GameState;
import com.blacksoft.ui.TileMarker;
import com.blacksoft.user.actions.UserAction;

import static com.blacksoft.state.Config.SCREEN_HEIGHT;
import static com.blacksoft.state.Config.SCREEN_WIDTH;

public class BuilderScreen extends ScreenAdapter {

    private SpriteBatch spriteBatch;
    private TiledMapRenderer tiledMapRenderer;
    private RayHandler rayHandler;
    private World world;
    private ShapeRenderer shapeRenderer;

    public BuilderScreen(SpriteBatch spriteBatch) {
        this.spriteBatch = spriteBatch;
    }

    @Override
    public void show() {
        // CAMERA, VIEWPORT
        GameState.orthographicCamera = new OrthographicCamera();
        GameState.orthographicUICamera = new OrthographicCamera();
        GameState.viewport = new FitViewport(SCREEN_WIDTH / 2f, SCREEN_HEIGHT / 2f, GameState.orthographicCamera);
        GameState.uiViewport = new FitViewport(SCREEN_WIDTH / 2f, SCREEN_HEIGHT / 2f, GameState.orthographicUICamera);
        GameState.viewport.apply(true);
        GameState.uiViewport.apply(true);
        this.shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(GameState.orthographicCamera.combined);


        // STAGE
        GameState.stage = new Stage(GameState.viewport);
        GameState.uiStage = new Stage(GameState.uiViewport);

        // UI
        GameState.uiStage.addActor(UIFactory.I.getFpsIndicator());
        UIFactory.I.createSelectionMarker();
        UIFactory.I.createBattleSelectionCursor();

        // LIGHT
        world = new World(new Vector2(0, 0), true);
        rayHandler = new RayHandler(world);
        rayHandler.setBlur(true);
        RayHandler.useDiffuseLight(true);
        rayHandler.setBlurNum(3);
        rayHandler.setAmbientLight(1f, 1f, 1f, 1f);
        rayHandler.setShadows(true);
        GameState.rayHandler = rayHandler;
        GameState.mouseLightSource = new PointLight(rayHandler, 15, new Color(1, 1f, 1f, 1f), 128, 0, 0);

        // MISC
        GameState.tileMarker = new TileMarker();
        GameState.tileMarker.setVisible(false);
        GameState.userAction = UserAction.Clean;

        // INPUT
        GameState.stage.addListener(new ClickListener(Input.Buttons.RIGHT) {

        });

        GameState.stage.addListener(new InputListener() {

            @Override
            public boolean keyDown(InputEvent event,
                                   int keycode) {
                if(keycode == Input.Keys.SPACE) {
                    GameState.paused = !GameState.paused;
                    return true;
                }

                return false;
            }

            @Override
            public boolean keyUp(InputEvent event,
                                 int keycode) {

                if (Input.Keys.ESCAPE == keycode) {
                    System.exit(0);
                }
                return false;
            }
        });

        GameState.uiStage.addListener(new ClickListener(Input.Buttons.RIGHT) {
            @Override
            public void clicked(InputEvent event,
                                float x,
                                float y) {

                if(GameState.isCombatSequence) {
                    if(GameState.userAction == UserAction.SelectSingleTarget) {
                        GameState.userAction = UserAction.CancelLast;
                        GameState.nextBattleAction = null;

                        // disable other skills
                        GameState.battleSkillIcons.get(GameState.battleSelectedCreature).forEach(UIFactory.I::enableSkill);
                    }
                }
            }
        });

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(GameState.uiStage);
        inputMultiplexer.addProcessor(GameState.stage);

        Gdx.input.setInputProcessor(inputMultiplexer);

        GameState.orthographicCamera.translate(-20, -20);

        NewGameInitializer.init();
        this.tiledMapRenderer = new OrthogonalTiledMapRenderer(GameState.dungeon.tiledMap, spriteBatch);

        CleanIndicatorsAction.cleanAll(GameState.dungeon);
        CleanIndicatorUpdater.update(GameState.dungeon);

        // ACTIONS
        GameState.stage.addAction(new MoveLightToMouseAction());
    }

    @Override
    public void render(float delta) {
        GameState.viewport.apply();
        //GameState.orthographicCamera.update();
        spriteBatch.setProjectionMatrix(GameState.orthographicCamera.combined);
        spriteBatch.setColor(Color.WHITE);

        GameState.stage.act();

        super.render(delta);
        tiledMapRenderer.setView(GameState.orthographicCamera);
        tiledMapRenderer.render();

        GameState.stage.draw();

        rayHandler.setCombinedMatrix(GameState.orthographicCamera);
        rayHandler.updateAndRender();

//        shapeRenderer.setProjectionMatrix(GameState.orthographicCamera.combined);
//        shapeRenderer.setAutoShapeType(true);
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//
//        for(Node node : GameState.dungeon.streetsMap.keys()) {
//
//            for(Connection<Node> connection : GameState.dungeon.streetsMap.get(node)) {
//                shapeRenderer.line(connection.getFromNode().x * 16 + 8, connection.getFromNode().y * 16 + 8, connection.getToNode().x * 16+ 8, connection.getToNode().y * 16 + 8);
//            }
//        }
//        shapeRenderer.end();

        GameState.uiViewport.apply();
        //GameState.orthographicUICamera.update();
        spriteBatch.setProjectionMatrix(GameState.orthographicUICamera.combined);
        GameState.uiStage.act();
        GameState.uiStage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
        rayHandler.dispose();
        spriteBatch.dispose();
    }
}
