package com.blacksoft.creature.action;

import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.blacksoft.creature.*;
import com.blacksoft.dungeon.phase.GamePhase;
import com.blacksoft.screen.UIFactory;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;
import com.blacksoft.state.UIState;

import static com.blacksoft.state.Config.DUNGEON_ENTRANCE_LOCATION;
import static com.blacksoft.state.Config.TEXTURE_SIZE;

public class CreatureSpawnerAction extends Action {

    private float graveyardSpawnDuration = 0f;
    private float oozeSpawnDuration = 0f;
    private float vampireSpawnDuration = 0f;
    private float warlockSpawnDuration = 0f;

    public boolean act(float v) {

        if (GameState.paused || GameState.gamePhase != GamePhase.Build) {
            return false;
        }

        graveyardSpawnDuration += v;
        oozeSpawnDuration += v;
        vampireSpawnDuration += v;
        warlockSpawnDuration += v;

        if (graveyardSpawnDuration >= Config.GRAVEYARD_SPAWN_TIME_LIMIT && GameState.skeletonLimit > GameState.creatures.stream().filter(creature -> Skeleton.class.isAssignableFrom(creature.getClass())).count()) {

            Creature skeleton = new Skeleton();
            GameState.stage.addActor(skeleton);
            GameState.creatures.add(skeleton);
            skeleton.setPosition((int) DUNGEON_ENTRANCE_LOCATION.x * TEXTURE_SIZE, (int) DUNGEON_ENTRANCE_LOCATION.y * TEXTURE_SIZE);
            skeleton.addAction(new CreatureDecisionAction());
            skeleton.addAction(Actions.fadeOut(0.1f));
            skeleton.addAction(Actions.fadeIn(10));
            UIFactory.I.addCreatureListEntry(skeleton);

            addHighlightAction(skeleton);

            graveyardSpawnDuration = 0;

            return false;
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

            addHighlightAction(ooze);

            oozeSpawnDuration = 0;

            return false;
        }

        if (vampireSpawnDuration >= Config.VAMPIRE_SPAWN_TIME_LIMIT && GameState.vampireLimit > GameState.creatures.stream().filter(creature -> Vampire.class.isAssignableFrom(creature.getClass())).count()) {

            Creature vampire = new Vampire();
            GameState.stage.addActor(vampire);
            GameState.creatures.add(vampire);
            vampire.setPosition((int) DUNGEON_ENTRANCE_LOCATION.x * TEXTURE_SIZE, (int) DUNGEON_ENTRANCE_LOCATION.y * TEXTURE_SIZE);
            vampire.addAction(new CreatureDecisionAction());
            UIFactory.I.addCreatureListEntry(vampire);
            addHighlightAction(vampire);

            vampireSpawnDuration = 0;

            return false;
        }

        if (warlockSpawnDuration >= Config.WARLOCK_SPAWN_TIME_LIMIT && GameState.warlockLimit > GameState.creatures.stream().filter(creature -> Warlock.class.isAssignableFrom(creature.getClass())).count()) {

            Creature warlock = new Warlock();
            GameState.stage.addActor(warlock);
            GameState.creatures.add(warlock);
            warlock.setPosition((int) DUNGEON_ENTRANCE_LOCATION.x * TEXTURE_SIZE, (int) DUNGEON_ENTRANCE_LOCATION.y * TEXTURE_SIZE);
            warlock.addAction(new CreatureDecisionAction());
            UIFactory.I.addCreatureListEntry(warlock);
            addHighlightAction(warlock);

            warlockSpawnDuration = 0;

            return false;
        }

        return false;
    }


    private void addHighlightAction(Creature creature) {

        creature.setTouchable(Touchable.enabled);
        creature.setBounds(creature.getX(), creature.getY(), 16, 16);

        creature.addListener(new InputListener() {

            @Override
            public void enter(InputEvent event,
                              float x,
                              float y,
                              int pointer,
                              Actor fromActor) {
                Table table = GameState.creatureListEntries.get(creature);
                table.getChild(0).setScale(1.5f);
                table.setBackground(UIState.selectionBackgroundHighlighted);
                table.setPosition(table.getX() - 1, table.getY());

            }

            @Override
            public void exit(InputEvent event,
                             float x,
                             float y,
                             int pointer,
                             Actor toActor) {
                UIState.selectionMarker.setVisible(false);
                Table table = GameState.creatureListEntries.get(creature);
                table.getChild(0).setScale(1f);
                table.setBackground(UIState.selectionBackground);
                table.setPosition(table.getX() + 1, table.getY());
            }
        });
    }

}
