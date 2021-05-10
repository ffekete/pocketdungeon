package com.blacksoft;

import com.blacksoft.dungeon.Dungeon;
import com.blacksoft.dungeon.actions.AbstractAction;
import com.blacksoft.dungeon.actions.ActionLevel;
import com.blacksoft.dungeon.actions.PlaceGraveyardAction;
import com.blacksoft.dungeon.init.DungeonInitializer;
import com.blacksoft.state.GameState;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class NewGameInitializer {

    public static void init() {
        Dungeon dungeon = new Dungeon();
        GameState.dungeon = dungeon;
        DungeonInitializer.init(dungeon);

        GameState.unlockedActions = Arrays.asList(PlaceGraveyardAction.I);
        ActionsHandler.I.initActions();

        // three basic actions
        GameState.actions.add(ActionsHandler.I.draw(ActionLevel.Basic));
        GameState.actions.add(ActionsHandler.I.draw(ActionLevel.Basic));
        GameState.actions.add(ActionsHandler.I.draw(ActionLevel.Basic));

        // two random actions
        GameState.actions.add(ActionsHandler.I.clone(GameState.unlockedActionsPrioritized.get(new Random().nextInt(GameState.unlockedActionsPrioritized.size()))));
        GameState.actions.add(ActionsHandler.I.clone(GameState.unlockedActionsPrioritized.get(new Random().nextInt(GameState.unlockedActionsPrioritized.size()))));
    }



}
