package com.blacksoft.dungeon.objects;

import box2dLight.Light;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.blacksoft.dungeon.Tile;
import com.blacksoft.dungeon.lighting.FlickeringLightAction;
import com.blacksoft.dungeon.lighting.LightSourceFactory;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;

public class Torch extends AbstractMapObject {

    private static TextureRegion textureRegion;
    private static TextureRegion textureRegion2;
    private static Animation<TextureRegion> animation;

    static {
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("tile/Torch.png")));
        textureRegion2 = new TextureRegion(new Texture(Gdx.files.internal("tile/Torch2.png")));
        Array<TextureRegion> regions = new Array<>();
        regions.add(textureRegion, textureRegion2);
        animation = new Animation<>(0.25f, regions);
    }

    public int x, y;
    private float animationStateTime = 0f;

    public int level = 1;
    private Light lightSource;
    private FlickeringLightAction flickeringLightAction;

    @Override
    public void place(int x,
                      int y) {
        GameState.loopProgress += Config.TORCH_PROGRESS_VALUE;

        GameState.oozeLimit += 1;
        this.lightSource = LightSourceFactory.getTorchLightSource(x / 16 * 16 + 8, y / 16 * 16 + 8);
        this.flickeringLightAction = new FlickeringLightAction(this.lightSource);
        GameState.stage.addAction(flickeringLightAction);
        this.x = x / 16;
        this.y = y / 16;
    }

    @Override
    public void destroy() {
        GameState.oozeLimit -= level;
        lightSource.setActive(false);
        lightSource.remove();
    }

    @Override
    public Tile getTile() {
        return Tile.Torch;
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
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }
}
