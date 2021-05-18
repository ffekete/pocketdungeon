package com.blacksoft.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.blacksoft.creature.Creature;
import com.blacksoft.dungeon.actions.AbstractAction;
import com.blacksoft.dungeon.actions.ui.CleanIndicatorsAction;
import com.blacksoft.dungeon.actions.ui.UpgradeIndicatorUpdater;
import com.blacksoft.screen.action.AddActorAction;
import com.blacksoft.screen.action.MovePlaceableActorToMouseAction;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;
import com.blacksoft.state.UIState;
import com.blacksoft.ui.AnimatedDrawable;
import com.blacksoft.ui.AnimatedImage;
import com.blacksoft.ui.DynamicLabel;
import com.blacksoft.ui.DynamicProgressBar;
import com.blacksoft.ui.action.FollowCreatureAction;
import com.blacksoft.ui.action.IntAction;
import com.blacksoft.user.actions.UserAction;

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

    public Group getStatusBar() {

        Group horizontalGroup = new Group();

        UIState.statusBar = horizontalGroup;

        Table table = new Table();

//        Label progressLabel = new Label(Integer.toString(GameState.loopProgress), labelStyle14);
//        UIState.progressLabel = progressLabel;
//        progressLabel.setColor(Color.valueOf("e3a858"));
//        Label progressTextLabel = new Label("Progress", labelStyle14);
//        progressTextLabel.setColor(Color.LIGHT_GRAY);
//        table.add(progressTextLabel).width(50);
//        table.add(progressLabel).width(30).left();
//        updateLabelAmount(0, GameState.loopProgress, progressLabel, "%s", null);

        // create progress bar
        ProgressBar.ProgressBarStyle invasionProgressBarStyle = new ProgressBar.ProgressBarStyle();
        invasionProgressBarStyle.background = new TextureRegionDrawable(UIState.progressBarBackgroundImage);
        TextureRegion[] textureRegions = TextureRegion.split(UIState.invasionProgressBarKnobImage.getTexture(), 10, 10)[0];
        Animation<TextureRegion> timeKnobAnimation = new Animation<>(0.3f, textureRegions);
        invasionProgressBarStyle.knob = new AnimatedDrawable(timeKnobAnimation);
        ProgressBar invasionProgressBar = new ProgressBar(0, 100, 1f, false, invasionProgressBarStyle);
        UIState.invasionProgressBar = invasionProgressBar;

        table.add(new Image(UIState.GoldIconImage)).size(12).padRight(2);
        Label goldLabel = new Label("", labelStyle14);
        goldLabel.setColor(Color.valueOf("e3a858"));
        UIState.goldLabel = goldLabel;
        table.add(goldLabel).width(60).left();

        updateLabelAmount(0, GameState.gold, goldLabel, "%s", null);

        Label ironLabel = new Label("", labelStyle14);
        ironLabel.setColor(Color.LIGHT_GRAY);
        UIState.ironLabel = ironLabel;
        table.add(new Image(UIState.IronIconImage)).size(16).padRight(2);
        table.add(ironLabel).width(60).left();
        updateLabelAmount(0, GameState.iron, ironLabel, "%s/%s", GameState.maxIronCapacity);

        Label gemLabel = new Label("", labelStyle14);
        UIState.gemLabel = gemLabel;
        gemLabel.setColor(Color.valueOf("50C878"));
        table.add(new Image(UIState.GemIconImage)).size(14).padRight(2);
        table.add(gemLabel).width(60).left();
        updateLabelAmount(0, GameState.gems, gemLabel, "%s/%s", GameState.maxGemsCapacity);

        // Add progress bars
        table.add(invasionProgressBar).height(10).width(60).left();

        horizontalGroup.addActor(new Image(new Texture(Gdx.files.internal("ui/StatusBar.png"))));
        horizontalGroup.addActor(table);

        table.setFillParent(true);
        horizontalGroup.setPosition(0, SCREEN_HEIGHT / 2 - 60);
        horizontalGroup.setSize(480, 32);

        return horizontalGroup;
    }

    public void createLockImages() {
        Image openLockImage = new Image(new Texture(Gdx.files.internal("ui/OpenLock.png")));
        Image closedLockImage = new Image(new Texture(Gdx.files.internal("ui/ClosedLock.png")));

        openLockImage.setVisible(false);
        closedLockImage.setVisible(false);

        openLockImage.setColor(1, 1, 1, 0.5f);
        closedLockImage.setColor(1, 1, 1, 0.5f);

        UIState.openLockImage = openLockImage;
        UIState.closedLockImage = closedLockImage;

        GameState.uiStage.addActor(openLockImage);
        GameState.uiStage.addActor(closedLockImage);
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

        Image image = new Image(iconTextureRegion);
        image.setSize(16, 16);

        SequenceAction sequenceAction1 = new SequenceAction();
        sequenceAction1.addAction(Actions.moveTo(x + 2, y + 15, 0.8f));
        sequenceAction1.addAction(Actions.removeActor());
        sequenceAction1.setActor(table);

        sequenceAction.addAction(sequenceAction1);

        table.add(image).size(16).pad(0, 2, 0, 0);
        table.setColor(1, 1, 1, 0.8f);

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

        Image image = new Image(iconTextureRegion);
        image.setSize(16, 16);
        SequenceAction sequenceAction = new SequenceAction();
        sequenceAction.addAction(Actions.moveTo(x + 2, y + 15, 0.8f));
        sequenceAction.addAction(Actions.removeActor());
        table.addAction(sequenceAction);

        table.add(image).size(16).pad(0, 2, 0, 0);
        table.setColor(1, 1, 1, 0.8f);

        GameState.uiStage.addActor(table);

        return label;

    }

    public void createFloatingIcon(TextureRegion iconTextureRegion,
                                   int x,
                                   int y) {
        Table table = new Table();

        table.setPosition(x + 2, y);

        Image image = new Image(iconTextureRegion);
        image.setSize(16, 16);
        SequenceAction sequenceAction = new SequenceAction();
        sequenceAction.addAction(Actions.moveTo(x + 2, y + 15, 0.8f));
        sequenceAction.addAction(Actions.removeActor());
        table.addAction(sequenceAction);

        table.add(image).size(16).pad(0, 2, 0, 0);
        table.setColor(1, 1, 1, 0.8f);

        GameState.uiStage.addActor(table);
    }

    public void createFloatingIcon(TextureRegion iconTextureRegion,
                                   int x,
                                   int y,
                                   SequenceAction sequenceAction) {

        Table table = new Table();

        table.setPosition(x + 2, y);

        Image image = new Image(iconTextureRegion);
        image.setSize(16, 16);

        SequenceAction floatingAction = new SequenceAction();
        floatingAction.addAction(Actions.moveTo(x + 2, y + 15, 0.8f));
        floatingAction.addAction(Actions.removeActor());
        floatingAction.setActor(table);

        sequenceAction.addAction(floatingAction);

        table.add(image).size(16).pad(0, 2, 0, 0);
        table.setColor(1, 1, 1, 0.8f);

        GameState.uiStage.addActor(table);
    }

    public Label createFloatingLabel(int newAmount,
                                     int x,
                                     int y,
                                     SequenceAction sequenceAction,
                                     float delay) {
        Label label = new Label(Integer.toString(newAmount), labelStyle14);

        label.setPosition(x + 2, y);

        MoveToAction moveToAction = Actions.moveTo(x + 2, y + 10, 0.5f);
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

    public Group getActionsGroup() {

        Group group = new Group();
        group.setDebug(true);

        group.addActor(new Image(new Texture(Gdx.files.internal("ui/ActionPanel.png"))));

        HorizontalGroup horizontalGroup = new HorizontalGroup();
        UIState.actionsGroup = horizontalGroup;

        for (AbstractAction action : GameState.actions) {
            addAction(horizontalGroup, action);
        }

        group.setPosition(482, 0);
        horizontalGroup.setPosition(6, 128);
        horizontalGroup.setScale(2f);

        group.addActor(horizontalGroup);

        Container<DynamicLabel> descriptionGroup = new Container<>();
        descriptionGroup.setPosition(10, 100);
        descriptionGroup.setFillParent(true);
        descriptionGroup.top().left();

        group.addActor(descriptionGroup);

        DynamicLabel descriptionLabel = new DynamicLabel(labelStyle14, () -> GameState.highlightedAction != null ? getDescription() : "");
        descriptionGroup.setActor(descriptionLabel);

        return group;
    }

    public Group getCreatureListPanel() {

        Group group = new Group();

        group.addActor(new Image(new Texture(Gdx.files.internal("ui/CreatureListPanel.png"))));

        group.setPosition(482, 156);
        group.setSize(180, 200);

        Table table = new Table();
        table.pad(15, 15, 15, 15);
        table.setFillParent(true);
        table.top().left();

        ScrollPane scrollPane = new ScrollPane(table);
        group.addActor(scrollPane);
        scrollPane.setFillParent(true);

        UIState.creatureListTable = table;

        return group;
    }

    public void addCreatureListEntry(Creature creature) {

        Table table = new Table();
        AnimatedImage animatedImage = new AnimatedImage(creature.getAnimation());
        table.add(animatedImage).size(16).left().pad(3, 5, 3, 7);

        Table statsTable = new Table();

        // HP progress bar
        DynamicProgressBar hpDynamicProgressBar = createHpProgressBar(creature, 16, 5);
        statsTable.add(hpDynamicProgressBar).size(16, 5).pad(1).row();

        // MP progress bar
        DynamicProgressBar mpDynamicProgressBar = createMpProgressBar(creature, 16, 5);
        statsTable.add(mpDynamicProgressBar).size(16, 5).pad(1);

        table.add(statsTable);

        DynamicLabel mpLabel = new DynamicLabel(labelStyle14, () -> String.valueOf(creature.mp));
        table.add(new Label("mp:", labelStyle14));
        table.add(mpLabel).size(16);

        DynamicLabel levelLabel = new DynamicLabel(labelStyle14, () -> String.valueOf(creature.level));
        table.add(new Label("lv:", labelStyle14));
        table.add(levelLabel).expandX().fillX();

        UIState.creatureListTable.add(table).fillX().expandX();

        GameState.creatureListEntries.put(creature, table);

        FollowCreatureAction followCreatureAction = new FollowCreatureAction(creature, () -> null);

        GameState.followCreatureAction = followCreatureAction;

        table.setBackground(UIState.selectionBackground);

        table.addListener(new InputListener() {

            @Override
            public void enter(InputEvent event,
                              float x,
                              float y,
                              int pointer,
                              Actor fromActor) {

                table.getChild(0).setScale(1.5f);
                table.setBackground(UIState.selectionBackgroundHighlighted);
                table.setPosition(table.getX() - 1, table.getY());

                GameState.creatureListEntries.entrySet().stream()
                        .filter(entry -> entry.getValue() == table)
                        .map(entry -> entry.getKey())
                        .findFirst()
                        .ifPresent(creature2 -> {
                            UIState.selectionMarker.setPosition(creature2.getX(), creature2.getY());
                            UIState.selectionMarker.setVisible(true);
                            followCreatureAction.setCreature(creature2);
                            followCreatureAction.setTarget(() -> UIState.selectionMarker);
                            creature2.addAction(followCreatureAction);
                        });
            }

            @Override
            public void exit(InputEvent event,
                             float x,
                             float y,
                             int pointer,
                             Actor toActor) {
                UIState.selectionMarker.setVisible(false);

                table.getChild(0).setScale(1f);
                table.setBackground(UIState.selectionBackground);
                table.setPosition(table.getX() + 1, table.getY());

                GameState.creatureListEntries.entrySet().stream()
                        .filter(entry -> entry.getValue() == table)
                        .map(entry -> entry.getKey())
                        .findFirst()
                        .ifPresent(creature2 -> {
                            creature2.removeAction(followCreatureAction);
                        });
            }
        });

        UIState.creatureListTable.row();
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
                1,
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

        UIState.selectionMarker.setVisible(false);

        GameState.uiStage.addActor(animatedImage);
    }

    public void addAction(Group horizontalGroup,
                          AbstractAction action) {
        ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.imageUp = new TextureRegionDrawable(action.getTexture());
        imageButtonStyle.imageDown = new TextureRegionDrawable(action.getTexture());
        ImageButton image = new ImageButton(imageButtonStyle);
        image.pad(0.5f);

        image.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event,
                              float x,
                              float y,
                              int pointer,
                              Actor fromActor) {
                if (GameState.userAction != UserAction.Place) {
                    image.setY(image.getY() + 2);
                    GameState.highlightedAction = action;
                    GameState.highlightedActionImage = image;
                }
            }

            @Override
            public void exit(InputEvent event,
                             float x,
                             float y,
                             int pointer,
                             Actor toActor) {
                if (GameState.userAction != UserAction.Place) {
                    image.setY(image.getY() - 2);
                    GameState.highlightedAction = null;
                    GameState.highlightedActionImage = null;
                }
            }

            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {

                if (GameState.userAction == UserAction.Place) {
                    // already placing, cannot place until the current item is placed
                    return true;
                }

                GameState.selectedActionImage = image;

                GameState.userAction = UserAction.Place;
                CleanIndicatorsAction.cleanAll(GameState.dungeon);

                GameState.selectedAction = action;

                UpgradeIndicatorUpdater.update(GameState.dungeon);

                ParallelAction moveAndScaleAction = new ParallelAction();

                RemoveActorAction removeActorAction = new RemoveActorAction();
                image.setTransform(true);
                removeActorAction.setTarget(image);

                SequenceAction sequenceAction = new SequenceAction();
                moveAndScaleAction.addAction(Actions.scaleTo(2f, 2f, 0.2f));
                moveAndScaleAction.addAction(Actions.moveTo(image.getX(), image.getY() + 40, 0.2f));

                sequenceAction.addAction(moveAndScaleAction);
                sequenceAction.addAction(Actions.visible(false));
                sequenceAction.addAction(new AddActorAction(image, GameState.stage));
                sequenceAction.addAction(Actions.scaleTo(1f, 1f));
                sequenceAction.addAction(Actions.visible(true));
                sequenceAction.addAction(new MovePlaceableActorToMouseAction(image));

                image.addAction(sequenceAction);

                return true;
            }
        });

        image.setTransform(true);
        SequenceAction sequenceAction = new SequenceAction();
        sequenceAction.addAction(Actions.scaleTo(2f, 2f));
        sequenceAction.addAction(Actions.scaleTo(1f, 1f, 0.1f));

        image.addAction(sequenceAction);

        horizontalGroup.addActor(image);
    }

    private String getDescription() {
        return String.format("%s\n%s", GameState.highlightedAction.getTitle(), GameState.highlightedAction.getDescription());
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
