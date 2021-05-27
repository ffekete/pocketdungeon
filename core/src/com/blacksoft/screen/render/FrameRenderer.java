package com.blacksoft.screen.render;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blacksoft.dungeon.Dungeon;

import static com.blacksoft.state.Config.MAP_HEIGHT;
import static com.blacksoft.state.Config.MAP_WIDTH;

public class FrameRenderer {

    public static final FrameRenderer I = new FrameRenderer();

    TextureRegion lowerFrameTextureRegion;
    TextureRegion upperFrameTextureRegion;
    TextureRegion leftFrameTextureRegion;
    TextureRegion rightFrameTextureRegion;

    public FrameRenderer() {

        lowerFrameTextureRegion = new TextureRegion(Dungeon.frameTextureRegion);
        upperFrameTextureRegion = new TextureRegion(Dungeon.frameTextureRegion);
        leftFrameTextureRegion = new TextureRegion(Dungeon.frameTextureRegion);
        rightFrameTextureRegion = new TextureRegion(Dungeon.frameTextureRegion);

        lowerFrameTextureRegion.setRegion(16, 32, 16, 16);
        upperFrameTextureRegion.setRegion(16, 0, 16, 16);
        leftFrameTextureRegion.setRegion(0, 16, 16, 16);
        rightFrameTextureRegion.setRegion(32, 16, 16, 16);
    }

    public void render(SpriteBatch spriteBatch) {
        for (int i = 0; i < MAP_WIDTH; i++) {
            spriteBatch.draw(upperFrameTextureRegion, i * 16, MAP_HEIGHT * 16);
            spriteBatch.draw(lowerFrameTextureRegion, i * 16, -16);
        }

        for (int i = 0; i < MAP_HEIGHT; i++) {
            spriteBatch.draw(leftFrameTextureRegion, -16, i * 16);
            spriteBatch.draw(rightFrameTextureRegion, MAP_WIDTH * 16 , i * 16);
        }

        Dungeon.frameTextureRegion.setRegion(0, 0, 16 ,16);
        spriteBatch.draw(Dungeon.frameTextureRegion, -16, MAP_HEIGHT * 16);

        Dungeon.frameTextureRegion.setRegion(32, 0, 16 ,16);
        spriteBatch.draw(Dungeon.frameTextureRegion, MAP_WIDTH * 16, MAP_HEIGHT * 16);

        Dungeon.frameTextureRegion.setRegion(0, 32, 16 ,16);
        spriteBatch.draw(Dungeon.frameTextureRegion, -16, -16);

        Dungeon.frameTextureRegion.setRegion(32, 32, 16 ,16);
        spriteBatch.draw(Dungeon.frameTextureRegion, MAP_WIDTH * 16, -16);
    }
}

