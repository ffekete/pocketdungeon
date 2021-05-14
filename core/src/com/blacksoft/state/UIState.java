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

    // CARDS
    public static TextureRegionDrawable GraveyardCardImage = new TextureRegionDrawable(new Texture(Gdx.files.internal("card/GraveyardCard.png")));
    public static TextureRegionDrawable DoorCardImage = new TextureRegionDrawable(new Texture(Gdx.files.internal("card/DoorCard.png")));
    public static TextureRegionDrawable LibraryCardImage = new TextureRegionDrawable(new Texture(Gdx.files.internal("card/LibraryCard.png")));
    public static TextureRegionDrawable TorchCardImage = new TextureRegionDrawable(new Texture(Gdx.files.internal("card/TorchCard.png")));
    public static TextureRegionDrawable TreasuryCardImage = new TextureRegionDrawable(new Texture(Gdx.files.internal("card/TreasuryCard.png")));
    public static TextureRegionDrawable RestingAreaCardImage = new TextureRegionDrawable(new Texture(Gdx.files.internal("card/RestingAreaCard.png")));
    public static TextureRegionDrawable BloodPoolCardImage = new TextureRegionDrawable(new Texture(Gdx.files.internal("card/BloodPoolCard.png")));

}
