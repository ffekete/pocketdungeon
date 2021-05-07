package com.blacksoft.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;
import com.blacksoft.ui.DynamicLabel;

import static com.blacksoft.state.Config.SCREEN_HEIGHT;
import static com.blacksoft.state.Config.SCREEN_WIDTH;

public class UIFactory {

    public static final UIFactory I = new UIFactory();

    private BitmapFont bitmapFont12;
    private BitmapFont bitmapFont24;
    private Label.LabelStyle labelStyle12;
    private Label.LabelStyle labelStyle24;

    public UIFactory() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("ui/ARCADECLASSIC.ttf"));

        bitmapFont12 = getFont(generator, 12);
        labelStyle12 = new Label.LabelStyle();
        labelStyle12.font = bitmapFont12;

        bitmapFont24 = getFont(generator, 24);
        labelStyle24 = new Label.LabelStyle();
        labelStyle24.font = bitmapFont24;

        generator.dispose(); // don't forget to dispose to avoid memory leaks!
    }

    private BitmapFont getFont(FreeTypeFontGenerator generator,
                               int size) {
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        BitmapFont bitmapFont = generator.generateFont(parameter); // font size 12 pixels
        return bitmapFont;
    }

    public Label addMovingLabel(String text) {

        SequenceAction sequenceAction = new SequenceAction();

        Label label = new Label(text, labelStyle24);
        label.setColor(Color.GREEN);
        label.setPosition(-50, SCREEN_HEIGHT / 4f);

        sequenceAction.addAction(new DelayAction(0.5f));

        MoveToAction moveToAction2 = getMoveToAction(label, Config.SCREEN_WIDTH / 4f - 2, Config.SCREEN_HEIGHT / 4f);
        moveToAction2.setDuration(0.15f);
        sequenceAction.addAction(moveToAction2);

        MoveToAction moveToAction3 = getMoveToAction(label, Config.SCREEN_WIDTH / 5f - 2, Config.SCREEN_HEIGHT / 4f);
        moveToAction3.setDuration(2f);
        sequenceAction.addAction(moveToAction3);

        MoveToAction moveToAction = getMoveToAction(label, SCREEN_WIDTH + 50, Config.SCREEN_HEIGHT / 4f);
        moveToAction.setDuration(0.15f);
        sequenceAction.addAction(moveToAction);

        RemoveActorAction removeAction = new RemoveActorAction();
        removeAction.setTarget(label);
        sequenceAction.addAction(removeAction);

        label.addAction(sequenceAction);

        return label;
    }

    public Label addMovingLabelShadow(String text) {

        SequenceAction sequenceAction = new SequenceAction();

        Label label = new Label(text, labelStyle24);
        label.setColor(Color.GRAY);
        label.setPosition(SCREEN_WIDTH / 2f - 60, SCREEN_HEIGHT / 4f - 2);

        sequenceAction.addAction(new DelayAction(0.5f));

        MoveToAction moveToAction = getMoveToAction(label, Config.SCREEN_WIDTH / 4f, Config.SCREEN_HEIGHT / 4f - 2);
        moveToAction.setDuration(0.15f);
        sequenceAction.addAction(moveToAction);

        MoveToAction moveToAction3 = getMoveToAction(label, Config.SCREEN_WIDTH / 5f, Config.SCREEN_HEIGHT / 4f - 2);
        moveToAction3.setDuration(2f);
        sequenceAction.addAction(moveToAction3);

        MoveToAction moveToAction2 = getMoveToAction(label, -50, Config.SCREEN_HEIGHT / 4f - 2);
        moveToAction2.setDuration(0.15f);
        sequenceAction.addAction(moveToAction2);

        RemoveActorAction removeAction = new RemoveActorAction();
        removeAction.setTarget(label);
        sequenceAction.addAction(removeAction);

        label.addAction(sequenceAction);

        return label;
    }

    private MoveToAction getMoveToAction(Label label,
                                         float tx,
                                         float ty) {
        MoveToAction moveToAction = new MoveToAction();
        moveToAction.setTarget(label);
        moveToAction.setPosition(tx, ty);
        return moveToAction;
    }

    public Group getStatusBar() {

        HorizontalGroup horizontalGroup = new HorizontalGroup();

        horizontalGroup.addActor(new Label("Progress", labelStyle12));
        horizontalGroup.addActor(new Label("   ", labelStyle12));
        horizontalGroup.addActor(new DynamicLabel(labelStyle12, () -> Integer.toString(GameState.loopProgress)));
        horizontalGroup.addActor(new Label("          ", labelStyle12));

        horizontalGroup.addActor(new Label("Gold", labelStyle12));
        horizontalGroup.addActor(new Label("   ", labelStyle12));
        horizontalGroup.addActor(new DynamicLabel(labelStyle12, () -> Integer.toString(GameState.gold)));
        horizontalGroup.addActor(new Label("          ", labelStyle12));

        horizontalGroup.addActor(new Label("Iron", labelStyle12));
        horizontalGroup.addActor(new Label("   ", labelStyle12));
        horizontalGroup.addActor(new DynamicLabel(labelStyle12, () -> Integer.toString(GameState.iron)));
        horizontalGroup.addActor(new Label("          ", labelStyle12));

        horizontalGroup.addActor(new Label("Gems", labelStyle12));
        horizontalGroup.addActor(new Label("   ", labelStyle12));
        horizontalGroup.addActor(new DynamicLabel(labelStyle12, () -> Integer.toString(GameState.gems)));
        horizontalGroup.addActor(new Label("          ", labelStyle12));

        Group group = new Group();
        group.addActor(new Image(new Texture(Gdx.files.internal("ui/StatusBar.png"))));
        group.addActor(horizontalGroup);
        horizontalGroup.setDebug(true);
        horizontalGroup.setFillParent(true);
        horizontalGroup.center();
        group.setPosition(0, SCREEN_HEIGHT / 2 - 60);
        group.setSize(480, 32);

        return group;
    }

    public DynamicLabel getFpsIndicator() {
        DynamicLabel fpsIndicator = new DynamicLabel(labelStyle12, () -> Integer.toString(Gdx.graphics.getFramesPerSecond()));
        fpsIndicator.setPosition(SCREEN_WIDTH / 2 - 60, SCREEN_HEIGHT / 2 - 60);
        return fpsIndicator;
    }

}
