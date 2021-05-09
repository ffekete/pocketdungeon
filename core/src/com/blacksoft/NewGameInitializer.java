package com.blacksoft;

import com.blacksoft.dungeon.Dungeon;
import com.blacksoft.dungeon.init.DungeonInitializer;
import com.blacksoft.state.GameState;

public class NewGameInitializer {

    public static void init() {
        Dungeon dungeon = new Dungeon();
        GameState.dungeon = dungeon;
        DungeonInitializer.init(dungeon);
    }

}
