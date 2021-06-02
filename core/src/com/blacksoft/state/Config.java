package com.blacksoft.state;

import com.badlogic.gdx.math.Vector2;

public class Config {

    public static final int MAP_HEIGHT = 20;
    public static final int MAP_WIDTH = 40;

    public static final int TEXTURE_SIZE = 16;

    public static final int SCREEN_WIDTH = 1366;
    public static final int SCREEN_HEIGHT = 768;

    public static final Vector2 DUNGEON_ENTRANCE_LOCATION = new Vector2(0, MAP_HEIGHT / 2);
    public static final int WIZARD_MAX_HP = 5;
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

    public static final float FLOATING_ITEMS_DURATION = 0.8f;

    // battle screen layout
    public static int MAX_SKILLS_SIZE = 3;

    public static int SECTOR_SIZE = 5;

    public static int BATTLE_SCREEN_POS_X = (SCREEN_WIDTH / 2 - 300) / 2;
    public static int BATTLE_SCREEN_POS_Y = (SCREEN_HEIGHT / 2 - 200) / 2;
}
