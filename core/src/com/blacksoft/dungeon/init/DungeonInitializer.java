package com.blacksoft.dungeon.init;

import com.badlogic.gdx.math.Vector2;
import com.blacksoft.dungeon.Dungeon;
import com.blacksoft.dungeon.actions.TileCleaner;
import com.blacksoft.state.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.blacksoft.state.Config.MAP_HEIGHT;
import static com.blacksoft.state.Config.MAP_WIDTH;

public class DungeonInitializer {

    public static void init(Dungeon dungeon) {
        for (int k = 0; k < Config.STARTING_DUNGEON_LENGTH; k++) {

            List<Vector2> vectors = new ArrayList<>();

            for (int i = 0; i < MAP_WIDTH; i++) {
                for (int j = 0; j < MAP_HEIGHT; j++) {
                    if (TileCleaner.canClean(dungeon, i, j)) {
                        vectors.add(new Vector2(i, j));
                    }
                }
            }

            Vector2 vector2 = vectors.get(new Random().nextInt(vectors.size()));

            TileCleaner.clean(dungeon, (int) vector2.x, (int) vector2.y);
        }
    }
}
