package com.blacksoft.state;

import com.badlogic.gdx.math.Vector2;

public class Config {

    public static final int MAP_HEIGHT = 20;
    public static final int MAP_WIDTH = 30;

    public static final int TEXTURE_SIZE = 16;

    public static final int SCREEN_WIDTH = 1366;
    public static final int SCREEN_HEIGHT = 768;

    public static final int STARTING_DUNGEON_LENGTH = 0;

    public static final float TIME_PERIOD = 12;

    // priorities
    public static final int GRAVEYARD_PRIORITY = 5;
    public static final int TORCH_PRIORITY = 5;
    public static final int BLOOD_POOL_PRIORITY = 3;
    public static final int TREASURY_PRIORITY = 5;
    public static final int RESTING_AREA_PRIORITY = 5;
    public static final int GATE_PRIORITY = 5;
    public static final int LIBRARY_PRIORITY = 2;

    // progress values
    public static final int CLEAN_PROGRESS_VALUE = 5;
    public static final int GRAVEYARD_PROGRESS_VALUE = 10;
    public static final int TORCH_PROGRESS_VALUE = 10;
    public static final int BLOOD_POOL_PROGRESS_VALUE = 20;
    public static final int TREASURY_PROGRESS_VALUE = 20;
    public static final int RESTING_AREA_PROGRESS_VALUE = 10;
    public static final int GATE_PROGRESS_VALUE = 10;
    public static final int LIBRARY_PROGRESS_VALUE = 10;

    // spawn limits
    public static final int GRAVEYARD_SPAWN_TIME_LIMIT = 0;
    public static final int OOZE_SPAWN_TIME_LIMIT = 0;
    public static final int VAMPIRE_SPAWN_TIME_LIMIT = 0;
    public static final int WARLOCK_SPAWN_TIME_LIMIT = 0;

    // salaries
    public static final int WARLOCK_SALARY_REQUEST = 200;
    public static final int VAMPIRE_SALARY_REQUEST = 100;
    public static final int SKELETON_SALARY_REQUEST = 0;
    public static final int OOZE_SALARY_REQUEST = 0;

    public static final float NO_SALARY_MORALE_PENALTY = 10f;

    public static final int TREASURY_INCOME_VALUE = 100;
    public static final int CLEAN_COST_VALUE = 10;

    public static final Vector2 DUNGEON_ENTRANCE_LOCATION = new Vector2(0, MAP_HEIGHT / 2);
    public static final int WIZARD_MAX_HP = 3;
    public static final int ROGUE_MAX_HP = 3;
    public static final int WARLOCK_MAX_HP = 6;
    public static final int VAMPIRE_MAX_HP = 8;
    public static final int OOZE_MAX_HP = 2;
    public static final int SKELETON_MAX_HP = 3;

    public static final int WIZARD_MAX_MP = 8;
    public static final int ROGUE_MAX_MP = 1;
    public static final int WARLOCK_MAX_MP = 8;
    public static final int VAMPIRE_MAX_MP = 3;
    public static final int OOZE_MAX_MP = 0;
    public static final int SKELETON_MAX_MP = 0;
}
