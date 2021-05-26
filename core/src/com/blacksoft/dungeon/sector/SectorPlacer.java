package com.blacksoft.dungeon.sector;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RemoveActorAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.blacksoft.dungeon.Dungeon;
import com.blacksoft.dungeon.Node;
import com.blacksoft.dungeon.actions.CameraShakeAction;
import com.blacksoft.dungeon.actions.MoveNodeToCoordAction;
import com.blacksoft.dungeon.effect.Dust;
import com.blacksoft.dungeon.sector.templates.SectorTemplate;
import com.blacksoft.screen.action.AddActorAction;
import com.blacksoft.state.GameState;
import com.blacksoft.ui.AnimatedImage;

import static com.blacksoft.state.Config.SECTOR_SIZE;
import static com.blacksoft.state.Config.TEXTURE_SIZE;

public class SectorPlacer {

    public static void place(SectorTemplate sectorTemplate, int sx, int sy, Dungeon dungeon, ParallelAction mainPlacementActon, float delay) {

        Node[][] nodes = sectorTemplate.toNodeMap();

        SequenceAction fullSequence = new SequenceAction();
        fullSequence.addAction(new DelayAction(delay));
        ParallelAction tileMovementAction = new ParallelAction();

        for (int i = 0; i < SECTOR_SIZE; i++) {
            for (int j = 0; j < SECTOR_SIZE; j++) {

                dungeon.replaceTileToNewTile(sx * SECTOR_SIZE + i, sy * SECTOR_SIZE + j, nodes[i][j].tile);

                if (nodes[i][j].object != null) {
                    dungeon.addObject(sx * SECTOR_SIZE + i, sy * SECTOR_SIZE + j, nodes[i][j].object);
                }

                dungeon.nodes[sx * SECTOR_SIZE + i][sy * SECTOR_SIZE + j].compatibility = sectorTemplate.getCompatibility();

                tileMovementAction.addAction(new MoveNodeToCoordAction(dungeon.nodes[sx * SECTOR_SIZE + i][sy * SECTOR_SIZE + j], 0, 400));
            }
        }

        ParallelAction dustAction = new ParallelAction();

        fullSequence.addAction(tileMovementAction);
        fullSequence.addAction(dustAction);

        dustAction.addAction(new CameraShakeAction(0.1f));

        // add dust animation

        RemoveActorAction removeActorAction = addDustAnimation(sx * SECTOR_SIZE * 16 - 16, sy * SECTOR_SIZE * 16, dustAction, false);
        dustAction.addAction(removeActorAction);

        RemoveActorAction removeActorAction2 = addDustAnimation(sx * SECTOR_SIZE * 16 + SECTOR_SIZE * 16, sy * SECTOR_SIZE * 16, dustAction, true);
        dustAction.addAction(removeActorAction2);
        mainPlacementActon.addAction(fullSequence);

    }

    private static RemoveActorAction addDustAnimation(int sx, int sy, ParallelAction fullSequence, boolean flip) {
        Animation<TextureRegion> dustAnimation = new Animation<>(0.05f, TextureRegion.split(Dust.texture, TEXTURE_SIZE, TEXTURE_SIZE)[0]);

        AnimatedImage animatedImage = new AnimatedImage(dustAnimation, false, flip);
        animatedImage.setPosition(sx, sy);
        fullSequence.addAction(new AddActorAction(animatedImage, GameState.stage));
        fullSequence.addAction(new DelayAction(0.45f));
        RemoveActorAction removeActorAction = new RemoveActorAction();
        removeActorAction.setActor(animatedImage);
        return removeActorAction;
    }

}
