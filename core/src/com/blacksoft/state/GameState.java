package com.blacksoft.state;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.blacksoft.battle.BattlePhase;
import com.blacksoft.creature.Creature;
import com.blacksoft.dungeon.Dungeon;
import com.blacksoft.dungeon.actions.AbstractAction;
import com.blacksoft.dungeon.building.Building;
import com.blacksoft.dungeon.pathfinder.CityGraph;
import com.blacksoft.dungeon.phase.GamePhase;
import com.blacksoft.hero.Party;
import com.blacksoft.skill.Skill;
import com.blacksoft.ui.AnimatedImage;
import com.blacksoft.ui.DynamicProgressBar;
import com.blacksoft.ui.TileMarker;
import com.blacksoft.ui.action.FollowCreatureAction;
import com.blacksoft.user.actions.UserAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameState {

    public static OrthographicCamera orthographicCamera;
    public static OrthographicCamera orthographicUICamera;
    public static Viewport viewport;
    public static Viewport uiViewport;
    public static Dungeon dungeon;
    public static Stage stage;
    public static Stage uiStage;
    public static TileMarker tileMarker;
    public static UserAction userAction = UserAction.Idle;

    public static RayHandler rayHandler;
    public static PointLight mouseLightSource;

    public static int loopProgress = -20;
    public static int gold = 100;
    public static int iron = 10;
    public static int maxIronCapacity = 20;
    public static int gems = 20;
    public static int maxGemsCapacity = 20;

    public static List<AbstractAction> unlockedActions = new ArrayList<>();
    public static List<AbstractAction> unlockedActionsPrioritized = new ArrayList<>();
    public static List<AbstractAction> actions = new ArrayList<>();

    public static AbstractAction highlightedAction = null;
    public static AbstractAction selectedAction = null;
    public static ImageButton highlightedActionImage = null;
    public static ImageButton selectedActionImage = null;

    public static Building currentBuilding = null;

    public static List<Creature> creatures = new ArrayList<>();

    public static int skeletonLimit = 0;
    public static int warlockLimit = 0;
    public static int oozeLimit = 0;
    public static int vampireLimit = 0;

    public static CityGraph cityGraph;

    public static Map<Creature, Table> creatureListEntriesOnSidePanel = new HashMap<>();

    public static FollowCreatureAction followCreatureAction;

    public static boolean paused;

    public static GamePhase gamePhase = GamePhase.Build;

    // invader party stuff
    public static Party party = null;
    public static List<Creature> creaturesInvolvedInBattle = null;

    // Battle stuff
    public static Creature battleSelectedCreature = null;
    public static Action nextBattleAction = null;
    public static Creature nextAttackTarget = null;
    public static AnimatedImage nextAttackTargetImage = null;
    public static Map<Creature, AnimatedImage> battleImages = new HashMap<>();
    public static Map<Creature, List<AnimatedImage>> battleSkillIcons = new HashMap<>();
    public static Map<Creature, List<DynamicProgressBar>> battleHpAndMpProgressBars = new HashMap<>();
    public static boolean isCombatSequence = false;
    public static BattlePhase battlePhase = BattlePhase.FinishTurn;
}
