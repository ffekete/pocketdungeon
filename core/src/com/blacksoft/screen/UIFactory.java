package com.blacksoft.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.blacksoft.creature.Creature;
import com.blacksoft.skill.Skill;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;
import com.blacksoft.state.UIState;
import com.blacksoft.ui.AnimatedImage;
import com.blacksoft.ui.DynamicLabel;
import com.blacksoft.ui.DynamicProgressBar;
import com.blacksoft.ui.action.IntAction;

import java.util.ArrayList;

import static com.blacksoft.state.Config.FLOATING_ITEMS_DURATION;
import static com.blacksoft.state.Config.SCREEN_HEIGHT;
import static com.blacksoft.state.Config.SCREEN_WIDTH;
import static com.blacksoft.state.Config.TEXTURE_SIZE;

public class UIFactory {

    public static final UIFactory I = new UIFactory();

    private BitmapFont bitmapFont12;
    private BitmapFont bitmapFont25;
    private BitmapFont bitmapFont30;
    private Label.LabelStyle labelStyle14;
    private Label.LabelStyle labelStyle25;
    private Label.LabelStyle labelStyle30;

    public UIFactory() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("ui/orange_kid.ttf"));

        bitmapFont12 = getFont(generator, 14);
        labelStyle14 = new Label.LabelStyle();
        labelStyle14.font = bitmapFont12;

        bitmapFont30 = getFont(generator, 30);
        labelStyle30 = new Label.LabelStyle();
        labelStyle30.font = bitmapFont30;

        bitmapFont25 = getFont(generator, 22);
        labelStyle25 = new Label.LabelStyle();
        labelStyle25.font = bitmapFont25;

        generator.dispose(); // don't forget to dispose to avoid memory leaks!
    }

    private BitmapFont getFont(FreeTypeFontGenerator generator,
                               int size) {
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        BitmapFont bitmapFont = generator.generateFont(parameter); // font size 12 pixels
        return bitmapFont;
    }

    public Label getLabel14(String text) {
        return new Label(text, labelStyle14);
    }

    public Label addMovingLabel(String text) {

        SequenceAction sequenceAction = new SequenceAction();

        Label label = new Label(text, labelStyle30);
        label.setColor(Color.GREEN);
        label.setPosition(-70, SCREEN_HEIGHT / 4f);

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

        Label label = new Label(text, labelStyle30);
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

    public void createBattleSelectionCursor() {

        Animation<TextureRegion> animation = new Animation<TextureRegion>(0.3f, TextureRegion.split(UIState.battleSelectionCursorImage.getTexture(), 16, 16)[0]);
        AnimatedImage animatedImage = new AnimatedImage(animation);
        UIState.battleSelectionCursor = animatedImage;
        GameState.uiStage.addActor(UIState.battleSelectionCursor);
        animatedImage.setVisible(false);
    }

    private MoveToAction getMoveToAction(Label label,
                                         float tx,
                                         float ty) {
        MoveToAction moveToAction = new MoveToAction();
        moveToAction.setTarget(label);
        moveToAction.setPosition(tx, ty);
        return moveToAction;
    }

    public Label createFloatingLabelWithIconFromString(String prefix,
                                             String suffix,
                                             TextureRegion iconTextureRegion,
                                             int x,
                                             int y,
                                             SequenceAction sequenceAction) {
        Table table = new Table();

        Label label = null;

        label = new Label(prefix, labelStyle14);
        table.setPosition(x + 2, y);
        table.add(label);

        int width = iconTextureRegion.getTexture().getHeight(); // the image is always NxN
        Image image = new AnimatedImage(new Animation<>(0.3f, TextureRegion.split(iconTextureRegion.getTexture(), width, width)[0]));

        image.setSize(16, 16);

        SequenceAction sequenceAction1 = new SequenceAction();
        sequenceAction1.addAction(Actions.moveTo(x + 2, y + 15, FLOATING_ITEMS_DURATION));
        sequenceAction1.addAction(Actions.removeActor());
        sequenceAction1.setActor(table);

        sequenceAction.addAction(sequenceAction1);

        table.add(image).size(16).pad(0, 2, 0, 2);

        table.add(new Label(suffix, labelStyle14));
        table.setColor(1, 1, 1, 1f);

        GameState.uiStage.addActor(table);

        return label;
    }

    public Label createTwoFloatingLabelsWithTwoIconFromString(String prefix,
                                                       String suffix,
                                                       TextureRegion prefixIconTextureRegion,
                                                       TextureRegion suffixIconTextureRegion,
                                                       int x,
                                                       int y,
                                                       SequenceAction sequenceAction) {
        Table table = new Table();

        Label label = null;

        label = new Label(prefix, labelStyle14);
        table.setPosition(x + 2, y);
        table.add(label);

        int width = prefixIconTextureRegion.getTexture().getHeight(); // the image is always NxN
        Image prefixImage = new AnimatedImage(new Animation<>(0.3f, TextureRegion.split(prefixIconTextureRegion.getTexture(), width, width)[0]));

        prefixImage.setSize(16, 16);

        table.add(prefixImage).size(16).pad(0, 2, 0, 2);
        table.add(new Label(suffix, labelStyle14));

        int suffixWidth = suffixIconTextureRegion.getTexture().getHeight(); // the image is always NxN
        Image suffixImage = new AnimatedImage(new Animation<>(0.3f, TextureRegion.split(suffixIconTextureRegion.getTexture(), suffixWidth, suffixWidth)[0]));

        suffixImage.setSize(16, 16);
        table.add(suffixImage).size(16).pad(0, 2, 0, 2);

        SequenceAction sequenceAction1 = new SequenceAction();
        sequenceAction.addAction(sequenceAction1);
        sequenceAction1.addAction(Actions.moveTo(x + 2, y + 15, FLOATING_ITEMS_DURATION));
        sequenceAction1.addAction(Actions.removeActor());
        sequenceAction1.setActor(table);
        table.setColor(1, 1, 1, 1f);

        GameState.uiStage.addActor(table);

        return label;
    }

    public Label createFloatingLabelWithIcon(int newAmount,
                                             TextureRegion iconTextureRegion,
                                             int x,
                                             int y,
                                             SequenceAction sequenceAction) {
        Table table = new Table();

        Label label = null;

        label = new Label(Integer.toString(newAmount), labelStyle14);
        table.setPosition(x + 2, y);
        table.add(label);

        int width = iconTextureRegion.getTexture().getHeight(); // the image is always NxN
        Image image = new AnimatedImage(new Animation<>(0.3f, TextureRegion.split(iconTextureRegion.getTexture(), width, width)[0]));

        image.setSize(16, 16);

        SequenceAction sequenceAction1 = new SequenceAction();
        sequenceAction1.addAction(Actions.moveTo(x + 2, y + 15, FLOATING_ITEMS_DURATION));
        sequenceAction1.addAction(Actions.removeActor());
        sequenceAction1.setActor(table);

        sequenceAction.addAction(sequenceAction1);

        table.add(image).size(16).pad(0, 2, 0, 0);
        table.setColor(1, 1, 1, 1f);

        GameState.uiStage.addActor(table);

        return label;
    }

    public Label createFloatingLabelWithIcon(int newAmount,
                                             TextureRegion iconTextureRegion,
                                             int x,
                                             int y) {
        Table table = new Table();

        Label label = null;

        label = new Label(Integer.toString(newAmount), labelStyle14);
        table.setPosition(x + 2, y);
        table.add(label);

        int width = iconTextureRegion.getTexture().getHeight(); // the image is always NxN
        Image image = new AnimatedImage(new Animation<>(0.3f, TextureRegion.split(iconTextureRegion.getTexture(), width, width)[0]));

        image.setSize(16, 16);
        SequenceAction sequenceAction = new SequenceAction();
        sequenceAction.addAction(Actions.moveTo(x + 2, y + 15, FLOATING_ITEMS_DURATION));
        sequenceAction.addAction(Actions.removeActor());
        table.addAction(sequenceAction);

        table.add(image).size(16).pad(0, 2, 0, 0);
        table.setColor(1, 1, 1, 1f);

        GameState.uiStage.addActor(table);

        return label;

    }

    public void createFloatingIcon(TextureRegion iconTextureRegion,
                                   int x,
                                   int y) {
        Table table = new Table();

        table.setPosition(x + 2, y);

        int width = iconTextureRegion.getTexture().getHeight(); // the image is always NxN
        Image image = new AnimatedImage(new Animation<>(0.3f, TextureRegion.split(iconTextureRegion.getTexture(), width, width)[0]));

        image.setSize(16, 16);
        SequenceAction sequenceAction = new SequenceAction();
        sequenceAction.addAction(Actions.moveTo(x + 2, y + 15, FLOATING_ITEMS_DURATION));
        sequenceAction.addAction(Actions.removeActor());
        table.addAction(sequenceAction);

        table.add(image).size(16).pad(0, 2, 0, 0);
        table.setColor(1, 1, 1, 1f);

        GameState.uiStage.addActor(table);
    }

    public void createFloatingIcon(TextureRegion iconTextureRegion,
                                   int x,
                                   int y,
                                   SequenceAction sequenceAction) {

        Table table = new Table();

        table.setPosition(x + 2, y);

        int width = iconTextureRegion.getTexture().getHeight(); // the image is always NxN
        Image image = new AnimatedImage(new Animation<>(0.3f, TextureRegion.split(iconTextureRegion.getTexture(), width, width)[0]));

        image.setSize(16, 16);

        SequenceAction floatingAction = new SequenceAction();
        floatingAction.addAction(Actions.moveTo(x + 2, y + 15, FLOATING_ITEMS_DURATION));
        floatingAction.addAction(Actions.removeActor());
        floatingAction.setActor(table);

        sequenceAction.addAction(floatingAction);

        table.add(image).size(16).pad(0, 2, 0, 0);
        table.setColor(1, 1, 1, 1f);

        GameState.uiStage.addActor(table);
    }

    public Label createFloatingLabel(int newAmount,
                                     int x,
                                     int y,
                                     SequenceAction sequenceAction,
                                     float delay) {
        Label label = new Label(Integer.toString(newAmount), labelStyle14);

        label.setPosition(x + 2, y);

        MoveToAction moveToAction = Actions.moveTo(x + 2, y + 10, FLOATING_ITEMS_DURATION);
        moveToAction.setActor(label);

        label.setVisible(false);
        VisibleAction visibleAction = new VisibleAction();
        visibleAction.setVisible(true);
        visibleAction.setActor(label);
        sequenceAction.addAction(Actions.delay(delay));
        sequenceAction.addAction(visibleAction);
        sequenceAction.addAction(moveToAction);


        GameState.uiStage.addActor(label);

        return label;
    }

    // self contained
    public Label createFloatingLabel(int newAmount,
                                     int x,
                                     int y) {
        SequenceAction sequenceAction = new SequenceAction();
        Label label = createFloatingLabel(newAmount, x, y, sequenceAction, 0f);

        RemoveActorAction removeActorAction = Actions.removeActor();
        removeActorAction.setActor(label);
        sequenceAction.addAction(removeActorAction);

        label.addAction(sequenceAction);

        label.toFront();

        GameState.uiStage.addActor(label);

        return label;
    }

    public DynamicLabel getFpsIndicator() {
        DynamicLabel fpsIndicator = new DynamicLabel(labelStyle14, () -> Integer.toString(Gdx.graphics.getFramesPerSecond()));
        fpsIndicator.setPosition(0, 10);
        return fpsIndicator;
    }

    public DynamicProgressBar createMpProgressBar(Creature creature,
                                                  int width,
                                                  int height) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLUE);
        pixmap.fill();
        drawFrame(pixmap, width, height);

        TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();


        ProgressBar.ProgressBarStyle mpProgressBarStyle = new ProgressBar.ProgressBarStyle();
        mpProgressBarStyle.background = drawable;

        pixmap = new Pixmap(0, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.fill();

        drawFrame(pixmap, width, height);

        drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();

        mpProgressBarStyle.knob = drawable;

        pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLUE);
        pixmap.fill();

        drawFrame(pixmap, width, height);

        drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();

        mpProgressBarStyle.knobBefore = drawable;


        DynamicProgressBar mpDynamicProgressBar = new DynamicProgressBar(0,
                1,
                1,
                false,
                mpProgressBarStyle,
                () -> (float) creature.mp,
                () -> (float) creature.getMaxMp());
        mpDynamicProgressBar.setSize(width, height);

        return mpDynamicProgressBar;
    }

    public DynamicProgressBar createHpProgressBar(Creature creature,
                                                  int widht,
                                                  int height) {

        Pixmap pixmap = new Pixmap(widht, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.fill();
        drawFrame(pixmap, widht, height);

        TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();

        ProgressBar.ProgressBarStyle hpProgressBarStyle = new ProgressBar.ProgressBarStyle();
        hpProgressBarStyle.background = drawable;

        pixmap = new Pixmap(0, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.GREEN);
        pixmap.fill();
        drawFrame(pixmap, widht, height);

        drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();

        hpProgressBarStyle.knob = drawable;

        pixmap = new Pixmap(widht, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.GREEN);
        pixmap.fill();
        drawFrame(pixmap, widht, height);
        drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();

        hpProgressBarStyle.knobBefore = drawable;

        DynamicProgressBar hpDynamicProgressBar = new DynamicProgressBar(0,
                10,
                1,
                false,
                hpProgressBarStyle,
                () -> (float) creature.getHp(),
                () -> (float) creature.getMaxHp());
        hpDynamicProgressBar.setSize(widht, height);

        return hpDynamicProgressBar;
    }

    private void drawFrame(Pixmap pixmap,
                           int width,
                           int height) {
        pixmap.setColor(Color.BLACK);
        for (int i = 1; i < width - 1; i++) {
            pixmap.drawPixel(i, 0);
            pixmap.drawPixel(i, height - 1);
        }

        for (int i = 1; i < height - 1; i++) {
            pixmap.drawPixel(0, i);
            pixmap.drawPixel(width - 1, i);
        }

        pixmap.setBlending(Pixmap.Blending.None); // before you start drawing pixels.

        pixmap.drawPixel(0, 0, 0x00000000);
        pixmap.drawPixel(0, height - 1, 0x00000000);
        pixmap.drawPixel(width - 1, 0, 0x00000000);
        pixmap.drawPixel(width - 1, height - 1, 0x00000000);

        pixmap.setBlending(Pixmap.Blending.SourceOver);
    }

    public void createSelectionMarker() {
        TextureRegion[] textureRegion = TextureRegion.split(new Texture(Gdx.files.internal("ui/SelectionMarker.png")), TEXTURE_SIZE, TEXTURE_SIZE)[0];
        Animation<TextureRegion> animation = new Animation<TextureRegion>(0.3f, textureRegion);
        AnimatedImage animatedImage = new AnimatedImage(animation);

        UIState.selectionMarker = animatedImage;
        UIState.selectionMarker.setScale(1f);
        UIState.selectionMarker.setVisible(false);

        GameState.uiStage.addActor(animatedImage);
    }

    public void disableSkill(Image image) {
        image.setColor(1, 0, 0, 0.2f);
    }

    public void enableSkill(Image image) {
        image.setColor(1, 1, 1, 1f);
    }

    public Cursor createCursor(String imagePath) {
        Pixmap pm = new Pixmap(Gdx.files.internal(imagePath));
        Cursor cursor = Gdx.graphics.newCursor(pm, 0, 0);
        pm.dispose();
        return cursor;
    }

    public static AnimatedImage createSkillIcon(Creature creature, Skill skill) {
        AnimatedImage skillImage = new AnimatedImage(
                new Animation<>(0.3f, TextureRegion.split(skill.getIcon(), 16, 16)[0]));

        UIFactory.I.disableSkill(skillImage);

        GameState.battleSkillIcons.computeIfAbsent(creature, value -> new ArrayList<>());
        GameState.battleSkillIcons.get(creature).add(skillImage);

        return skillImage;
    }

    public void updateLabelAmount(int old,
                                  int newValue,
                                  Label label,
                                  String template,
                                  Integer other) {
        if (label != null) {
            IntAction intAction = new IntAction(old, newValue, 0.5f, label, template, other);
            GameState.uiStage.addAction(intAction);
        }
    }

}
