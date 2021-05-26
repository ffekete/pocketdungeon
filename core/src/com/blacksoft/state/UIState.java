package com.blacksoft.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.blacksoft.screen.UIFactory;
import com.blacksoft.ui.AnimatedImage;

public class UIState {

    public static Label goldLabel;
    public static AnimatedImage selectionMarker;
    public static Table creatureListTable;
    public static AnimatedImage battleSelectionCursor;

    // CURSORS
    public static Cursor defaultCursor = UIFactory.I.createCursor("ui/cursor/Cursor.png");
    public static Cursor attackCursor = UIFactory.I.createCursor("ui/cursor/AttackCursor.png");

    // ICONS
public static TextureRegion hourglassIconImage = new TextureRegion(new Texture(Gdx.files.internal("ui/icon/HourglassIcon.png")));

    // UI elements
public static TextureRegion battleSelectionCursorImage = new TextureRegion(new Texture(Gdx.files.internal("ui/BattleSelectionCursor.png")));

    // BATTLE
    public static Group battleScreen;
    public static TextureRegion battleScreenBackground = new TextureRegion(new Texture(Gdx.files.internal("ui/BattleScreen.png")));
    public static TextureRegion meleeAttackAnimationsTexture = new TextureRegion(new Texture(Gdx.files.internal("skill/MeleeHitEffect.png")));
    public static TextureRegion poisonEffectTexture = new TextureRegion(new Texture(Gdx.files.internal("skill/PoisonEffect.png")));
}
