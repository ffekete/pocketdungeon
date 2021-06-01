package com.blacksoft.dungeon.fow;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.blacksoft.dungeon.Dungeon;
import com.blacksoft.dungeon.actions.TileTypeDetector;
import com.blacksoft.state.GameState;

import java.util.ArrayList;
import java.util.List;

public class VisibilityCalculator {

    public final int width;
    public final int height;

    public VisibilityCalculator(int width, int height) {
        this.width = width;
        this.height = height;
    }

    private List<Integer[]> midPointCircleDraw(int x_centre,
                                               int y_centre, int r) {

        List<Integer[]> points = new ArrayList<>();

        int x = r, y = 0;

        // Printing the initial point
        // on the axes after translation
        points.add(new Integer[]{x_centre - r, y_centre});
        points.add(new Integer[]{x_centre, y_centre - r});
        // When radius is zero only a single
        // point will be printed
        if (r > 0) {

            points.add(new Integer[]{x + x_centre, -y + y_centre});
            points.add(new Integer[]{y + x_centre, x + y_centre});
            points.add(new Integer[]{-y + x_centre, x + y_centre});
        }

        // Initialising the value of P
        int P = 1 - r;
        while (x > y) {

            y++;

            // Mid-point is inside or on the perimeter
            if (P <= 0)
                P = P + 2 * y + 1;

                // Mid-point is outside the perimeter
            else {
                x--;
                P = P + 2 * y - 2 * x + 1;
            }

            // All the perimeter points have already
            // been printed
            if (x < y)
                break;

            // Printing the generated point and its
            // reflection in the other octants after
            // translation
            points.add(new Integer[]{x + x_centre, y + y_centre});
            points.add(new Integer[]{-x + x_centre, y + y_centre});
            points.add(new Integer[]{x + x_centre, -y + y_centre});
            points.add(new Integer[]{-x + x_centre, -y + y_centre});

            // If the generated point is on the
            // line x = y then the perimeter points
            // have already been printed
            if (x != y) {
                points.add(new Integer[]{y + x_centre, x + y_centre});
                points.add(new Integer[]{-y + x_centre, x + y_centre});
                points.add(new Integer[]{y + x_centre, -x + y_centre});
                points.add(new Integer[]{-y + x_centre, -x + y_centre});
            }

        }
        return points;
    }

    private void line(int x, int y, int x2, int y2, boolean[][] visibilityMask, Dungeon map) {
        int w = x2 - x;
        int h = y2 - y;
        int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0;
        if (w < 0) dx1 = -1;
        else if (w > 0) dx1 = 1;
        if (h < 0) dy1 = -1;
        else if (h > 0) dy1 = 1;
        if (w < 0) dx2 = -1;
        else if (w > 0) dx2 = 1;
        int longest = Math.abs(w);
        int shortest = Math.abs(h);
        if (!(longest > shortest)) {
            longest = Math.abs(h);
            shortest = Math.abs(w);
            if (h < 0) dy2 = -1;
            else if (h > 0) dy2 = 1;
            dx2 = 0;
        }
        int numerator = longest >> 1;
        for (int i = 0; i <= longest; i++) {


            x = Math.max(x, 0);
            y = Math.max(y, 0);
            x = Math.min(x, width - 1);
            y = Math.min(y, height - 1);

            visibilityMask[x][y] = true;

            ((TiledMapTileLayer)GameState.dungeon.tiledMap.getLayers().get(Dungeon.FOW_LAYER)).setCell(x,y, null);

            if (!TileTypeDetector.canTraverse(map, x, y)) break;


            numerator += shortest;
            if (!(numerator < longest)) {
                numerator -= longest;
                x += dx1;
                y += dy1;
            } else {
                x += dx2;
                y += dy2;
            }
        }
    }


    public void calculateFor(Vector2 p, int range, Dungeon map) {

        List<Integer[]> points = midPointCircleDraw((int)p.x, (int)p.y, range);

        for (Integer[] point : points) {
            line((int) p.x, (int) p.y, point[0], point[1], GameState.dungeonFowLayer.explored, map);
        }
    }


}