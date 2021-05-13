package com.blacksoft.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.blacksoft.ui.AnimatedImage;

public class UIState {

    public static Group actionsGroup;
    public static Group statusBar;
    public static Label progressLabel;
    public static Label goldLabel;
    public static Label gemLabel;
    public static Label ironLabel;

    public static Image openLockImage;
    public static Image closedLockImage;

    public static AnimatedImage selectionMarker;

    public static Table creatureListTable;

    public static TextureRegionDrawable selectionBackground = new TextureRegionDrawable(new Texture(Gdx.files.internal("ui/SelectionBackground.png")));
    public static TextureRegionDrawable selectionBackgroundHighlighted = new TextureRegionDrawable(new Texture(Gdx.files.internal("ui/SelectionBackgroundHighlighted.png")));

}
