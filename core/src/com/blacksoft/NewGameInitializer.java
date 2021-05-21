package com.blacksoft;

import com.blacksoft.creature.action.CreatureSpawnerAction;
import com.blacksoft.dungeon.Dungeon;
import com.blacksoft.dungeon.actions.ActionLevel;
import com.blacksoft.dungeon.actions.build.*;
import com.blacksoft.dungeon.init.DungeonInitializer;
import com.blacksoft.state.GameState;

import java.util.Arrays;
import java.util.Random;

public class NewGameInitializer {

    public static void init() {
        Dungeon dungeon = new Dungeon();
        GameState.dungeon = dungeon;
        DungeonInitializer.init(dungeon);

        GameState.unlockedActions = Arrays.asList(PlaceGraveyardAction.I,
                PlaceTorchAction.I,
                PlaceVampireCoffinAction.I,
                PlaceTreasuryAction.I,
                PlaceRestingAreaAction.I,
                PlaceGateAction.I,
                PlaceLibraryAction.I
        );

        ActionsHandler.I.initActions();

        // three basic actions
        GameState.actions.add(ActionsHandler.I.draw(ActionLevel.Basic));
        GameState.actions.add(ActionsHandler.I.draw(ActionLevel.Basic));
        GameState.actions.add(ActionsHandler.I.draw(ActionLevel.Basic));

        // two random actions
        GameState.actions.add(ActionsHandler.I.clone(GameState.unlockedActionsPrioritized.get(new Random().nextInt(GameState.unlockedActionsPrioritized.size()))));
        GameState.actions.add(ActionsHandler.I.clone(GameState.unlockedActionsPrioritized.get(new Random().nextInt(GameState.unlockedActionsPrioritized.size()))));

        GameState.stage.addAction(new CreatureSpawnerAction());
    }


}
