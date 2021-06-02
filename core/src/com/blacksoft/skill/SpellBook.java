package com.blacksoft.skill;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.blacksoft.creature.Creature;
import com.blacksoft.screen.UIFactory;
import com.blacksoft.state.Config;
import com.blacksoft.state.UIState;
import com.blacksoft.ui.AnimatedImage;
import com.blacksoft.user.actions.UserAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.blacksoft.battle.BattleScreenInitializer.HERO_CELL_WIDTH;

public class SpellBook implements Skill {

    public static final Texture icon;

    public List<Skill> spells = new ArrayList<>();
    public Map<Skill, AnimatedImage> spellsAndIcons = new HashMap<>();

    public Table submenu = new Table();

    static {
        icon = new Texture(Gdx.files.internal("skill/SpellBook.png"));
    }

    public void addSpell(Skill skill, Creature creature) {
        this.spells.add(skill);
        AnimatedImage skillIcon = UIFactory.createSkillIcon(creature, skill);
        UIFactory.I.enableSkill(skillIcon);
        this.submenu.add(skillIcon).size(HERO_CELL_WIDTH / Config.MAX_SKILLS_SIZE - 2).pad(1).row();

        this.spellsAndIcons.put(skill, skillIcon);

        this.submenu.setVisible(false);
    }

    @Override
    public TriFunction<Creature, List<Creature>, List<Creature>, Action> getAction() {
        return ((creature, creatures, heroes) -> {
            return Actions.delay(1f);
        });
    }

    @Override
    public UserAction getUserAction() {
        return UserAction.SelectSingleTarget;
    }

    @Override
    public Texture getIcon() {
        return icon;
    }

    @Override
    public Cursor getCursor() {
        return UIState.attackCursor;
    }

    @Override
    public Table getSubmenu() {
        return submenu;
    }

    @Override
    public Map<Skill, AnimatedImage> getSkillsAndIcons() {
        return this.spellsAndIcons;
    }
}
