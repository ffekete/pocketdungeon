package com.blacksoft.skill;

import com.badlogic.gdx.graphics.Texture;
import com.blacksoft.creature.Creature;

import java.util.List;

public interface Skill {

    void act(List<Creature> creatures, List<Creature> heroes);

    Texture getIcon();

}
