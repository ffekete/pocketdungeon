package com.blacksoft.dungeon.building;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blacksoft.dungeon.Tile;
import com.blacksoft.dungeon.actions.GateOpenCheckAction;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;

public class Gate implements Building {

    public int level = 1;
    private boolean opened = true;
    public int x, y;
    public boolean locked = false;

    private static TextureRegion openedTextureRegion;
    private static TextureRegion closedTextureRegion;

    static {
        openedTextureRegion = new TextureRegion(new Texture(Gdx.files.internal("tile/GateOpened.png")));
        closedTextureRegion = new TextureRegion(new Texture(Gdx.files.internal("tile/GateClosed.png")));
    }

    @Override
    public void place(int x,
                      int y) {

        GameState.loopProgress += Config.GATE_PROGRESS_VALUE;

        GameState.stage.addAction(new GateOpenCheckAction(this, x, y));
        this.x = x / 16;
        this.y = y / 16;

        if (opened) {
            GameState.dungeon.replaceTileToBuilding(this.x, this.y, Tile.GateOpened);
        } else {
            GameState.dungeon.replaceTileToBuilding(this.x, this.y, Tile.GateClosed);
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public Tile getTile() {
        return opened ? Tile.GateClosed : Tile.GateOpened;
    }

    @Override
    public boolean getState() {
        return opened;
    }

    @Override
    public void toggleState() {

        if(locked) {
            return;
        }

        opened = !opened;
        if (opened) {
            GameState.dungeon.replaceTileToBuilding(x, y, Tile.GateOpened, false);
        } else {
            GameState.dungeon.replaceTileToBuilding(x, y, Tile.GateClosed, false);
        }
    }

    @Override
    public TextureRegion getTextureRegion() {
        return opened ? openedTextureRegion : closedTextureRegion;
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
