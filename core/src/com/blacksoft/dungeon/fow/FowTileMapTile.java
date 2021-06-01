package com.blacksoft.dungeon.fow;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.blacksoft.state.GameState;

import static com.blacksoft.state.Config.MAP_HEIGHT;
import static com.blacksoft.state.Config.MAP_WIDTH;

public class FowTileMapTile extends StaticTiledMapTile {

    public int x,y;
    private TextureRegion textureRegion;

    public FowTileMapTile(TextureRegion textureRegion, int x, int y) {
        super(textureRegion);
        this.x = x;
        this.y = y;
        this.textureRegion = textureRegion;
    }

    @Override
    public TextureRegion getTextureRegion() {
        TextureRegion currentTextureRegion;
        if (this.textureRegion == null) {
            currentTextureRegion = GameState.dungeon.nodes[x][y].object.getTextureRegion();
        } else {
            currentTextureRegion = this.textureRegion;
        }

        int v = 0;

        if (x == 0 || !GameState.dungeonFowLayer.explored[x - 1][y]) {
            v += 1;
        }

        if ((y >= MAP_HEIGHT - 1) || !GameState.dungeonFowLayer.explored[x][y + 1]) {
            v += 2;
        }

        if (y ==0 || !GameState.dungeonFowLayer.explored[x][y - 1]) {
            v += 8;
        }

        if (x == MAP_WIDTH - 1 || !GameState.dungeonFowLayer.explored[x + 1][y]) {
            v += 4;
        }

        if(v == 0) currentTextureRegion.setRegion(48, 48, 16 ,16);
        if(v == 1) currentTextureRegion.setRegion(32, 48, 16 ,16);
        if(v == 2) currentTextureRegion.setRegion(48, 32, 16 ,16);
        if(v == 3) currentTextureRegion.setRegion(32, 32, 16 ,16);
        if(v == 4) currentTextureRegion.setRegion(0, 48, 16 ,16);
        if(v == 5) currentTextureRegion.setRegion(16, 48, 16 ,16);
        if(v == 6) currentTextureRegion.setRegion(0, 32, 16 ,16);
        if(v == 7) currentTextureRegion.setRegion(16, 32, 16 ,16);
        if(v == 8) currentTextureRegion.setRegion(48, 0, 16 ,16);
        if(v == 9) currentTextureRegion.setRegion(32, 0, 16 ,16);
        if(v == 10) currentTextureRegion.setRegion(48, 16, 16 ,16);
        if(v == 11) currentTextureRegion.setRegion(32, 16, 16 ,16);
        if(v == 12) currentTextureRegion.setRegion(0, 0, 16 ,16);
        if(v == 13) currentTextureRegion.setRegion(16, 0, 16 ,16);
        if(v == 14) currentTextureRegion.setRegion(0, 16, 16 ,16);
        if(v == 15) currentTextureRegion.setRegion(16, 16, 16 ,16);


        return currentTextureRegion;

    }
}
