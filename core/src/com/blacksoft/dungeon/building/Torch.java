package com.blacksoft.dungeon.building;

import box2dLight.Light;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.blacksoft.dungeon.Tile;
import com.blacksoft.dungeon.actions.AbstractAction;
import com.blacksoft.dungeon.actions.build.PlaceTorchAction;
import com.blacksoft.dungeon.lighting.FlickeringLightAction;
import com.blacksoft.dungeon.lighting.LightSourceFactory;
import com.blacksoft.screen.UIFactory;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;
import com.blacksoft.state.UIState;

public class Torch implements Building {

    private static TextureRegion textureRegion;
    private static TextureRegion textureRegion2;
    private static TextureRegion textureRegion3;
    private static TextureRegion textureRegion4;
    private static Animation<TextureRegion> animation;

    static {
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("tile/Torch.png")));
        textureRegion2 = new TextureRegion(new Texture(Gdx.files.internal("tile/TorchSecondPhase.png")));
        textureRegion3 = new TextureRegion(new Texture(Gdx.files.internal("tile/TorchThirdPhase.png")));
        textureRegion4 = new TextureRegion(new Texture(Gdx.files.internal("tile/TorchFourthPhase.png")));
        Array<TextureRegion> regions = new Array<>();
        regions.add(textureRegion, textureRegion2, textureRegion3, textureRegion4);
        animation = new Animation<TextureRegion>(0.25f, regions);
    }

    public int x,y;
    private float animationStateTime = 0f;

    public int level = 1;
    private Light lightSource;
    private FlickeringLightAction flickeringLightAction;

    @Override
    public boolean canUpgradeBy(AbstractAction action) {
        return level < 5 && PlaceTorchAction.class.isAssignableFrom(action.getClass());
    }

    @Override
    public void place(int x,
                      int y) {
        int oldProgress = GameState.loopProgress;
        GameState.loopProgress += Config.TORCH_PROGRESS_VALUE;
        UIFactory.I.updateLabelAmount(oldProgress, GameState.loopProgress, UIState.progressLabel, "%s", null);

        GameState.oozeLimit
                += 0.5f;
        this.lightSource = LightSourceFactory.getTorchLightSource(x / 16 * 16 + 8, y / 16 * 16 + 8);
        this.flickeringLightAction = new FlickeringLightAction(this.lightSource);
        GameState.stage.addAction(flickeringLightAction);
        this.x = x / 16;
        this.y = y / 16;
    }

    @Override
    public void upgrade() {
        GameState.loopProgress += Config.TORCH_PROGRESS_VALUE;
        GameState.oozeLimit += 0.5f;
        level++;

        flickeringLightAction.setOriginalDistance(lightSource.getDistance() + level * 5);
    }

    @Override
    public void destroy() {
        GameState.oozeLimit -= 0.5f * level;
        lightSource.setActive(false);
        lightSource.remove();
    }

    @Override
    public Tile getTile() {
        return Tile.Torch;
    }

    @Override
    public int getUpgradeLevel() {
        return level;
    }

    @Override
    public boolean getState() {
        return false;
    }

    @Override
    public void toggleState() {

    }

    @Override
    public TextureRegion getTextureRegion() {
        animationStateTime += Gdx.graphics.getDeltaTime();
        return animation.getKeyFrame(animationStateTime, true);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}
