package com.blacksoft.dungeon.actions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.blacksoft.ActionsHandler;
import com.blacksoft.build.UserAction;
import com.blacksoft.screen.UIFactory;
import com.blacksoft.state.GameState;
import com.blacksoft.state.UIState;
import com.blacksoft.ui.AnimatedImage;

import java.util.Random;

import static com.blacksoft.state.Config.MAP_HEIGHT;
import static com.blacksoft.state.Config.MAP_WIDTH;

public class BuildingPlacer {

    private static Texture upgradeIndicatorTexture;

    static {
        upgradeIndicatorTexture = new Texture(Gdx.files.internal("ui/UpgradeIndicator.png"));
    }

    public static boolean canPlace(int x,
                                   int y) {
        int vx = x / 16;
        int vy = y / 16;

        if (vx >= 0 && vx < MAP_WIDTH && vy >= 0 && vy < MAP_HEIGHT) {

            if (GameState.userAction == UserAction.Place) {
                if (TileCleaner.isClean(GameState.dungeon, vx, vy) || GameState.dungeon.nodes[vx][vy].canUpgradeBy(GameState.selectedAction)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean canUpgrade(int x,
                                   int y) {
        int vx = x / 16;
        int vy = y / 16;

        if (vx >= 0 && vx < MAP_WIDTH && vy >= 0 && vy < MAP_HEIGHT) {

            if (GameState.userAction == UserAction.Place) {
                if (GameState.dungeon.nodes[vx][vy].canUpgradeBy(GameState.selectedAction)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void place(int x,
                             int y) {
        int vx = x / 16;
        int vy = y / 16;

        if (BuildingPlacer.canPlace(x, y)) {

            GameState.selectedAction.execute();

            SequenceAction sequenceAction = new SequenceAction();
            sequenceAction.addAction(Actions.scaleTo(3f, 3f));

            if (GameState.dungeon.nodes[vx][vy].building == null) { // build a new one
                GameState.dungeon.nodes[vx][vy].building = GameState.currentBuilding;
                GameState.dungeon.replaceTile(vx, vy, GameState.currentBuilding.getTile());
                GameState.dungeon.nodes[vx][vy].building.place(vx, vy);
            } else {
                // upgrade building
                GameState.dungeon.nodes[vx][vy].building.upgrade();
            }

            sequenceAction.addAction(Actions.scaleTo(1f, 1f, 0.2f));
            GameState.stage.addActor(UIFactory.I.updateProgress(GameState.selectedAction.getProgressAmount(), x / 16 * 16, y / 16 * 16));
            sequenceAction.addAction(Actions.removeActor());
            GameState.selectedActionImage.addAction(sequenceAction);

            GameState.selectedAction = null;
            GameState.userAction = UserAction.Clean;

            CleanIndicatorsAction.cleanAll(GameState.dungeon);
            CleanIndicatorUpdater.update(GameState.dungeon);

            // draw new action card
            AbstractAction action = ActionsHandler.I.clone(GameState.unlockedActionsPrioritized.get(new Random().nextInt(GameState.unlockedActionsPrioritized.size())));
            GameState.actions.add(action);
            UIFactory.I.addAction(UIState.actionsGroup, action);
        }
    }

}
