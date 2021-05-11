package com.blacksoft.state;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.blacksoft.build.UserAction;
import com.blacksoft.creature.Creature;
import com.blacksoft.dungeon.Dungeon;
import com.blacksoft.dungeon.actions.AbstractAction;
import com.blacksoft.dungeon.building.Building;
import com.blacksoft.ui.TileMarker;

import java.util.ArrayList;
import java.util.List;

public class GameState {

    public static OrthographicCamera orthographicCamera;
    public static OrthographicCamera orthographicUICamera;
    public static Viewport viewport;
    public static Viewport uiViewport;
    public static Dungeon dungeon;
    public static Stage stage;
    public static Stage uiStage;
    public static TileMarker tileMarker;
    public static UserAction userAction;

    public static RayHandler rayHandler;
    public static PointLight mouseLightSource;

    public static int loopProgress = -20;
    public static int gold = 100;
    public static int iron = 0;
    public static int gems = 0;

    public static List<AbstractAction> unlockedActions = new ArrayList<>();
    public static List<AbstractAction> unlockedActionsPrioritized = new ArrayList<>();
    public static List<AbstractAction> actions = new ArrayList<>();

    public static AbstractAction highlightedAction = null;
    public static AbstractAction selectedAction = null;
    public static ImageButton highlightedActionImage = null;
    public static ImageButton selectedActionImage = null;

    public static Building currentBuilding = null;

    public static List<Creature> creatures = new ArrayList<>();

    public static float skeletonLimit = 0;
    public static float oozeLimit = 0;
    public static float vampireLimit = 0;


}
