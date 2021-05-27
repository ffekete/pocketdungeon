package com.blacksoft;

import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.blacksoft.dungeon.Dungeon;
import com.blacksoft.dungeon.init.DungeonInitializer;
import com.blacksoft.screen.action.ContinuousFollowCameraAction;
import com.blacksoft.screen.action.FollowCameraAction;
import com.blacksoft.screen.action.KeyPressedAction;
import com.blacksoft.screen.action.ZoomCameraToTargetAction;
import com.blacksoft.state.GameState;

public class NewGameInitializer {

    public static void init() {
        Dungeon dungeon = new Dungeon();
        GameState.dungeon = dungeon;

        SequenceAction initAction = new SequenceAction();
        DungeonInitializer.init(dungeon, initAction);

        PartyLoader.load(initAction);

        KeyPressedAction keyPressedAction = new KeyPressedAction();
        keyPressedAction.setActor(GameState.party);
        GameState.stage.addAction(keyPressedAction);

        initAction.addAction(new FollowCameraAction());
        initAction.addAction(new ZoomCameraToTargetAction());
        initAction.addAction(new ContinuousFollowCameraAction());

        GameState.stage.addAction(initAction);

    }


}
