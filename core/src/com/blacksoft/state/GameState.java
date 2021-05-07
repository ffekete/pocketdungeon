package com.blacksoft.state;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.blacksoft.build.BuildTool;
import com.blacksoft.dungeon.Dungeon;
import com.blacksoft.ui.TileMarker;

public class GameState {

    public static OrthographicCamera orthographicCamera;
    public static Viewport viewport;
    public static Dungeon dungeon;
    public static Stage stage;
    public static TileMarker tileMarker;
    public static BuildTool buildTool;

}
