package com.blacksoft.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.utils.Array;
import com.blacksoft.dungeon.Tile;

import java.util.Arrays;

import static com.blacksoft.state.Config.TEXTURE_SIZE;

public class TileAnimationProvider {

    public static TextureRegion[] getFor(Tile tile) {
        Texture animTexture = new Texture(Gdx.files.internal(String.format("tile/%s.png", tile.name())));
        return TextureRegion.split(animTexture, TEXTURE_SIZE, TEXTURE_SIZE)[0];
    }

    public static AnimatedTiledMapTile getAnimatedTiledMapTileForAnimation(String file) {
        Array<StaticTiledMapTile> array = new Array<>();

        Arrays.stream(TextureRegion.split(new Texture(Gdx.files.internal(file)), 16 ,16)[0])
                .map(StaticTiledMapTile::new)
                .forEach(array::add);

        return new AnimatedTiledMapTile(0.3f, array);
    }

    public static AnimatedTiledMapTile getAnimatedTiledMapTile(Tile tile) {
        Array<StaticTiledMapTile> array = new Array<>();
        Arrays.stream(TileAnimationProvider.getFor(tile))
                .map(StaticTiledMapTile::new)
                .forEach(array::add);

        return new AnimatedTiledMapTile(0.3f, array);
    }

}
