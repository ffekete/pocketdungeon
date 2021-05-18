package com.blacksoft.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.blacksoft.ui.AnimatedImage;

public class UIState {

    public static Group actionsGroup;
    public static Group statusBar;
    public static Label goldLabel;
    public static Label gemLabel;
    public static Label ironLabel;
    public static Image openLockImage;
    public static Image closedLockImage;
    public static AnimatedImage selectionMarker;
    public static Table creatureListTable;
    public static ProgressBar timeProgressBar;
    public static ProgressBar invasionProgressBar;
    public static AnimatedImage battleSelectionCursor;

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

    // ICONS
    public static TextureRegion GoldIconImage = new TextureRegion(new Texture(Gdx.files.internal("ui/icon/GoldIcon.png")));
    public static TextureRegion IronIconImage = new TextureRegion(new Texture(Gdx.files.internal("ui/icon/IronIcon.png")));
    public static TextureRegion GemIconImage = new TextureRegion(new Texture(Gdx.files.internal("ui/icon/GemIcon.png")));

    // UI elements
    public static TextureRegion progressBarBackgroundImage = new TextureRegion(new Texture(Gdx.files.internal("ui/InvasionProgressBar.png")));
    public static TextureRegion invasionProgressBarKnobImage = new TextureRegion(new Texture(Gdx.files.internal("ui/ProgressBarKnob.png")));
    public static TextureRegion battleSelectionCursorImage = new TextureRegion(new Texture(Gdx.files.internal("ui/BattleSelectionCursor.png")));

    // BATTLE
    public static Group battleScreen;
    public static TextureRegion battleScreenBackground = new TextureRegion(new Texture(Gdx.files.internal("ui/BattleScreen.png")));
    public static TextureRegion meleeAttackAnimationsTexture = new TextureRegion(new Texture(Gdx.files.internal("skill/MeleeHitEffect.png")));
    public static TextureRegion poisonEffectTexture = new TextureRegion(new Texture(Gdx.files.internal("skill/PoisonEffect.png")));
}
