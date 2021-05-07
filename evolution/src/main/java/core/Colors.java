package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Colors {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public Map<Character, List<String>> colorCombinations = new HashMap<>();

    public Colors() {
        Icons.I.obstacleIcons.forEach(icon -> {
            colorCombinations.put(icon, new ArrayList<>());
            colorCombinations.get(icon).add(ANSI_BLUE);
            colorCombinations.get(icon).add(ANSI_PURPLE);
            colorCombinations.get(icon).add(ANSI_WHITE);
        });
    }
}
