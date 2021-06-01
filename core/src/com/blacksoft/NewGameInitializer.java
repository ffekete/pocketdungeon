package com.blacksoft;

import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.blacksoft.dungeon.fow.action.UpdateFowAction;
import com.blacksoft.dungeon.generator.CastleDungeonGenerator;
import com.blacksoft.dungeon.init.DungeonInitializer;
import com.blacksoft.screen.action.ContinuousFollowCameraAction;
import com.blacksoft.screen.action.FollowCameraAction;
import com.blacksoft.screen.action.KeyPressedAction;
import com.blacksoft.screen.action.ZoomCameraToTargetAction;
import com.blacksoft.state.GameState;

public class NewGameInitializer {

    public static void init() {

        SequenceAction initAction = new SequenceAction();
        DungeonInitializer.init(initAction, new CastleDungeonGenerator());

        PartyLoader.load(initAction);

        KeyPressedAction keyPressedAction = new KeyPressedAction();
        keyPressedAction.setActor(GameState.party);
        GameState.stage.addAction(keyPressedAction);

        initAction.addAction(new FollowCameraAction());
        initAction.addAction(new ZoomCameraToTargetAction());

        ParallelAction followAndUpdateFowAction = new ParallelAction();

        followAndUpdateFowAction.addAction(new UpdateFowAction());
        followAndUpdateFowAction.addAction(new ContinuousFollowCameraAction());

        initAction.addAction(followAndUpdateFowAction);

        GameState.stage.addAction(initAction);

    }


}
