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

    public static final Gson GSON = new Gson();

    public static void init() {
        Dungeon dungeon = new Dungeon();
        GameState.dungeon = dungeon;
        DungeonInitializer.init(dungeon);

        GameState.unlockedActions = Arrays.asList(PlaceGraveyardAction.I);
        initActions();

        List<AbstractAction> basicActions = getActionOfType(ActionLevel.Basic);

        // three basic actions
        GameState.actions.add(clone(basicActions.get(new Random().nextInt(basicActions.size()))));
        GameState.actions.add(clone(basicActions.get(new Random().nextInt(basicActions.size()))));
        GameState.actions.add(clone(basicActions.get(new Random().nextInt(basicActions.size()))));

        // two random actions
        GameState.actions.add(clone(GameState.unlockedActionsPrioritized.get(new Random().nextInt(GameState.unlockedActionsPrioritized.size()))));
        GameState.actions.add(clone(GameState.unlockedActionsPrioritized.get(new Random().nextInt(GameState.unlockedActionsPrioritized.size()))));
    }

    private static AbstractAction clone(AbstractAction abstractAction) {
        return GSON.fromJson(GSON.toJson(abstractAction), abstractAction.getClass());
    }

    private static List<AbstractAction> getActionOfType(ActionLevel actionLevel) {
        List<AbstractAction> actions = new ArrayList<>();
        GameState.unlockedActionsPrioritized.stream()
                .filter(action -> action.getActionLevel().equals(actionLevel))
                .forEach(abstractAction -> {
                    for (int i = 0; i < abstractAction.getPriority(); i++) {
                        actions.add(abstractAction);
                    }
                });

        return actions;
    }

    private static void initActions() {
        for (AbstractAction unlockedAction : GameState.unlockedActions) {
            for (int j = 0; j < unlockedAction.getPriority(); j++) {
                GameState.unlockedActionsPrioritized.add(unlockedAction);
            }
        }
    }

}
