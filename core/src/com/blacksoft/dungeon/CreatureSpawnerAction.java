package com.blacksoft.dungeon;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.blacksoft.creature.Creature;
import com.blacksoft.creature.Ooze;
import com.blacksoft.creature.Skeleton;
import com.blacksoft.creature.action.WanderingAction;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.blacksoft.state.Config.MAP_HEIGHT;
import static com.blacksoft.state.Config.MAP_WIDTH;
import static com.blacksoft.state.Config.TEXTURE_SIZE;

public class CreatureSpawnerAction extends Action {

    private float graveyardSpawnDuration = 0f;
    private float oozeSpawnDuration = 5f;

    public boolean act(float v) {

        graveyardSpawnDuration += v;
        oozeSpawnDuration += v;


        List<Vector2> graveyards = new ArrayList<>();
        List<Vector2> torches = new ArrayList<>();

        if (graveyardSpawnDuration >= Config.GRAVEYARD_SPAWN_TIME_LIMIT && GameState.skeletonLimit > GameState.creatures.stream().filter(creature -> Skeleton.class.isAssignableFrom(creature.getClass())).count()) {

            for (int i = 0; i < MAP_WIDTH; i++) {
                for (int j = 0; j < MAP_HEIGHT; j++) {
                    if (GameState.dungeon.nodes[i][j].tile == Tile.GraveYard) {
                        graveyards.add(new Vector2(i, j));
                    }
                }
            }

            if (graveyards.size() > 0) {
                Vector2 randomVector = graveyards.get(new Random().nextInt(graveyards.size()));

                Creature skeleton = new Skeleton();
                GameState.stage.addActor(skeleton);
                GameState.creatures.add(skeleton);
                skeleton.setPosition(randomVector.x * TEXTURE_SIZE, randomVector.y * TEXTURE_SIZE);
                skeleton.addAction(new WanderingAction());
                skeleton.addAction(Actions.fadeOut(0.1f));
                skeleton.addAction(Actions.fadeIn(10));
            }

            graveyardSpawnDuration = 0;
        }


        if (oozeSpawnDuration >= Config.OOZE_SPAWN_TIME_LIMIT && GameState.oozeLimit > GameState.creatures.stream().filter(creature -> Ooze.class.isAssignableFrom(creature.getClass())).count()) {

            for (int i = 0; i < MAP_WIDTH; i++) {
                for (int j = 0; j < MAP_HEIGHT; j++) {
                    if (GameState.dungeon.nodes[i][j].tile == Tile.Torch) {
                        torches.add(new Vector2(i, j));
                    }
                }
            }

            if (torches.size() > 0) {
                Vector2 randomVector = torches.get(new Random().nextInt(torches.size()));

                Creature ooze = new Ooze();
                GameState.stage.addActor(ooze);
                GameState.creatures.add(ooze);
                ooze.setPosition(randomVector.x * TEXTURE_SIZE, randomVector.y * TEXTURE_SIZE);
                ooze.addAction(new WanderingAction());
                ooze.addAction(Actions.fadeOut(0.1f));
                ooze.addAction(Actions.fadeIn(10));
            }

            oozeSpawnDuration = 0;
        }

        return false;
    }


}
