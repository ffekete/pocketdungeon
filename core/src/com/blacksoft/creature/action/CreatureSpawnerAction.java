package com.blacksoft.creature.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.blacksoft.creature.Creature;
import com.blacksoft.creature.Ooze;
import com.blacksoft.creature.Skeleton;
import com.blacksoft.creature.Vampire;
import com.blacksoft.screen.UIFactory;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;

import java.util.ArrayList;
import java.util.List;

import static com.blacksoft.state.Config.DUNGEON_ENTRANCE_LOCATION;
import static com.blacksoft.state.Config.TEXTURE_SIZE;

public class CreatureSpawnerAction extends Action {

    private float graveyardSpawnDuration = 0f;
    private float oozeSpawnDuration = 5f;
    private float vampireSpawnDuration = 10f;

    public boolean act(float v) {

        graveyardSpawnDuration += v;
        oozeSpawnDuration += v;
        vampireSpawnDuration += v;


        List<Vector2> graveyards = new ArrayList<>();
        List<Vector2> torches = new ArrayList<>();
        List<Vector2> bloodPools = new ArrayList<>();

        if (graveyardSpawnDuration >= Config.GRAVEYARD_SPAWN_TIME_LIMIT && GameState.skeletonLimit > GameState.creatures.stream().filter(creature -> Skeleton.class.isAssignableFrom(creature.getClass())).count()) {

            Creature skeleton = new Skeleton();
            GameState.stage.addActor(skeleton);
            GameState.creatures.add(skeleton);
            skeleton.setPosition((int) DUNGEON_ENTRANCE_LOCATION.x * TEXTURE_SIZE, (int) DUNGEON_ENTRANCE_LOCATION.y * TEXTURE_SIZE);
            skeleton.addAction(new CreatureDecisionAction());
            skeleton.addAction(Actions.fadeOut(0.1f));
            skeleton.addAction(Actions.fadeIn(10));
            UIFactory.I.addCreatureListEntry(skeleton);

            graveyardSpawnDuration = 0;
        }


        if (oozeSpawnDuration >= Config.OOZE_SPAWN_TIME_LIMIT && GameState.oozeLimit > GameState.creatures.stream().filter(creature -> Ooze.class.isAssignableFrom(creature.getClass())).count()) {

            Creature ooze = new Ooze();
            GameState.stage.addActor(ooze);
            GameState.creatures.add(ooze);
            ooze.setPosition((int) DUNGEON_ENTRANCE_LOCATION.x * TEXTURE_SIZE, (int) DUNGEON_ENTRANCE_LOCATION.y * TEXTURE_SIZE);
            ooze.addAction(new CreatureDecisionAction());
            ooze.addAction(Actions.fadeOut(0.1f));
            ooze.addAction(Actions.fadeIn(10));
            UIFactory.I.addCreatureListEntry(ooze);

            oozeSpawnDuration = 0;
        }

        if (vampireSpawnDuration >= Config.VAMPIRE_SPAWN_TIME_LIMIT && GameState.vampireLimit > GameState.creatures.stream().filter(creature -> Vampire.class.isAssignableFrom(creature.getClass())).count()) {

            Creature vampire = new Vampire();
            GameState.stage.addActor(vampire);
            GameState.creatures.add(vampire);
            vampire.setPosition((int) DUNGEON_ENTRANCE_LOCATION.x * TEXTURE_SIZE, (int) DUNGEON_ENTRANCE_LOCATION.y * TEXTURE_SIZE);
            vampire.addAction(new CreatureDecisionAction());
            vampire.addAction(Actions.fadeOut(0.1f));
            vampire.addAction(Actions.fadeIn(10));
            UIFactory.I.addCreatureListEntry(vampire);

            vampireSpawnDuration = 0;
        }

        return false;
    }


}
