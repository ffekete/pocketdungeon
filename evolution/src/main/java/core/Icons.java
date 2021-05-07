package core;

import java.util.ArrayList;
import java.util.List;

public class Icons {

    public static final Icons I = new Icons();

    public List<Character> animalIcons = new ArrayList<>();
    public List<Character> plantlIcons = new ArrayList<>();
    public List<Character> obstacleIcons = new ArrayList<>();

    public Icons() {
        animalIcons.add('@');
        animalIcons.add('$');
        animalIcons.add('0');
        animalIcons.add('£');
        animalIcons.add('#');
        animalIcons.add('€');
        animalIcons.add('*');
        animalIcons.add('~');

        plantlIcons.add('T');
        plantlIcons.add('t');
        plantlIcons.add('I');
        plantlIcons.add('.');
        plantlIcons.add('•');
        plantlIcons.add(',');
        plantlIcons.add('/');
        plantlIcons.add('\\');
        plantlIcons.add('|');

        obstacleIcons.add('A');
        obstacleIcons.add('M');

    }


}
