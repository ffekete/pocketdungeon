package com.blacksoft.dungeon.pathfinder;

import com.badlogic.gdx.ai.pfa.Heuristic;
import com.badlogic.gdx.math.Vector2;
import com.blacksoft.dungeon.Node;

public class CityHeuristic  implements Heuristic<Node> {

  @Override
  public float estimate(Node currentCity, Node goalCity) {
    return Vector2.dst(currentCity.x, currentCity.y, goalCity.x, goalCity.y);
  }
}