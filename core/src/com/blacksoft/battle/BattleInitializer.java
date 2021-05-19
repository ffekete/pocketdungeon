package com.blacksoft.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.blacksoft.battle.action.BattleFlowAction;
import com.blacksoft.creature.Creature;
import com.blacksoft.hero.Party;
import com.blacksoft.screen.UIFactory;
import com.blacksoft.skill.Skill;
import com.blacksoft.state.GameState;
import com.blacksoft.state.UIState;
import com.blacksoft.ui.AnimatedImage;
import com.blacksoft.ui.DynamicProgressBar;
import com.blacksoft.user.actions.UserAction;

import java.util.ArrayList;
import java.util.List;

public class BattleInitializer {

    public static void init(List<Creature> creatures,
                            Party party) {

        Container<Table> container = new Container<>();

        Table battleScreen = new Table();
        container.setActor(battleScreen);
        container.setPosition(90, 60);
        container.setSize(300, 200);

        UIState.battleScreen = battleScreen;

        battleScreen.background(new TextureRegionDrawable(UIState.battleScreenBackground));
        battleScreen.setSize(300, 200);

        GameState.uiStage.addActor(UIFactory.I.addMovingLabel("BATTLE"));
        GameState.uiStage.addActor(UIFactory.I.addMovingLabelShadow("BATTLE"));

        GameState.userAction = UserAction.Idle;

        BattleSequence.reset();
        GameState.battleImages.clear();
        GameState.battleSkillIcons.clear();
        GameState.battleHpAndMpProgressBars.clear();

        int index = Math.max(party.heroes.size(), creatures.size());
        index = Math.min(4, index);

        for (int i = 0; i < index; i++) {
            // CREATURE
            if (i < creatures.size()) {
                Creature creature = creatures.get(i);
                AnimatedImage creatureImage = new AnimatedImage(creatures.get(i).getAnimation());

                battleScreen.add(creatureImage).size(48, 48).center().colspan(3);
                GameState.battleImages.put(creature, creatureImage);
                BattleSequence.add(creature);
            } else {
                battleScreen.add().size(48, 48).colspan(3);
            }

            // SEPARATOR
            battleScreen.add().size(48, 48);

            // HERO
            if (i < party.heroes.size()) {
                AnimatedImage heroImage = new AnimatedImage(party.heroes.get(i).getAnimation());
                battleScreen.add(heroImage).size(48, 48).center().colspan(3);
                party.heroes.get(i).setPosition(0, 0);

                Creature hero = party.heroes.get(i);
                BattleSequence.add(hero);

                GameState.battleImages.put(hero, heroImage);

                heroImage.addListener(new InputListener() {

                    @Override
                    public void enter(InputEvent event,
                                      float x,
                                      float y,
                                      int pointer,
                                      Actor fromActor) {
                        UIState.selectionMarker.setVisible(true);
                        UIState.selectionMarker.toFront();
                        UIState.selectionMarker.setPosition(heroImage.getX() + 90 + 16, heroImage.getY() + 60 + 48);
                    }

                    @Override
                    public void exit(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     Actor toActor) {
                        UIState.selectionMarker.setVisible(false);
                    }

                    @Override
                    public boolean touchDown(InputEvent event,
                                             float x,
                                             float y,
                                             int pointer,
                                             int button) {

                        if (GameState.nextBattleAction != null) {
                            GameState.nextAttackTarget = hero;
                            GameState.nextAttackTargetImage = heroImage;
                            Gdx.graphics.setCursor(UIState.defaultCursor);
                        }

                        return true;
                    }
                });

            } else {
                battleScreen.add().size(48, 48).colspan(3);
            }
            battleScreen.row();

            // hp bars for creatures
            if (i < creatures.size()) {
                Creature creature = creatures.get(i);
                GameState.battleHpAndMpProgressBars.computeIfAbsent(creature, value -> new ArrayList<>());

                DynamicProgressBar hpProgressBr = UIFactory.I.createHpProgressBar(creature, 48, 5);
                battleScreen.add(hpProgressBr).size(48, 5).colspan(3).pad(1);

                GameState.battleHpAndMpProgressBars.get(creature).add(hpProgressBr);
            } else {
                // empty fillers
                battleScreen.add().size(48, 5).colspan(3);
            }

            battleScreen.add().size(48, 5);

            // hp bars for heroes
            if (i < party.heroes.size()) {
                Creature hero = party.heroes.get(i);
                GameState.battleHpAndMpProgressBars.computeIfAbsent(hero, value -> new ArrayList<>());

                DynamicProgressBar hpProgressBr = UIFactory.I.createHpProgressBar(hero, 48, 5);
                battleScreen.add(hpProgressBr).size(48, 5).colspan(3).pad(1);

                GameState.battleHpAndMpProgressBars.get(hero).add(hpProgressBr);
            } else {
                // empty fillers
                battleScreen.add().size(48, 5).colspan(3);
            }

            battleScreen.row();

            // mp bars for creatures
            if (i < creatures.size()) {
                Creature creature = creatures.get(i);
                GameState.battleHpAndMpProgressBars.computeIfAbsent(creature, value -> new ArrayList<>());

                DynamicProgressBar mpProgressBar = UIFactory.I.createMpProgressBar(creature, 48, 5);
                battleScreen.add(mpProgressBar).size(48, 5).colspan(3).pad(1);

                GameState.battleHpAndMpProgressBars.get(creature).add(mpProgressBar);
            } else {
                // empty fillers
                battleScreen.add().size(48, 5).colspan(3);
            }

            battleScreen.add().size(48, 5);

            // mp and mp bars for heroes
            if (i < party.heroes.size()) {
                Creature hero = party.heroes.get(i);
                GameState.battleHpAndMpProgressBars.computeIfAbsent(hero, value -> new ArrayList<>());

                DynamicProgressBar mpProgressBar = UIFactory.I.createMpProgressBar(hero, 48, 5);
                battleScreen.add(mpProgressBar).size(48, 5).colspan(3).pad(1);

                GameState.battleHpAndMpProgressBars.get(hero).add(mpProgressBar);
            } else {
                // empty fillers
                battleScreen.add().size(48, 5).colspan(3);
            }

            battleScreen.row();

            // SKILLS
            int skillsIndex = 0;
            if (i < creatures.size()) {

                Creature creature = creatures.get(i);

                skillsIndex = creature.skills.size();
                for (int j = 0; j < skillsIndex; j++) {

                    Skill skill = creature.skills.get(j);

                    AnimatedImage skillImage = new AnimatedImage(
                            new Animation<>(0.3f, TextureRegion.split(creature.skills.get(j).getIcon(), 16, 16)[0]));
                    battleScreen.add(skillImage).size(16).pad(5);

                    GameState.battleSkillIcons.computeIfAbsent(creature, value -> new ArrayList<>());
                    GameState.battleSkillIcons.get(creature).add(skillImage);

                    skillImage.addListener(new InputListener() {
                        @Override
                        public void enter(InputEvent event,
                                          float x,
                                          float y,
                                          int pointer,
                                          Actor fromActor) {
                            skillImage.setScale(1.1f);
                        }

                        @Override
                        public void exit(InputEvent event,
                                         float x,
                                         float y,
                                         int pointer,
                                         Actor toActor) {
                            skillImage.setScale(1f);
                        }

                        @Override
                        public boolean touchDown(InputEvent event,
                                                 float x,
                                                 float y,
                                                 int pointer,
                                                 int button) {

                            if (GameState.userAction == UserAction.Idle) {
                                Gdx.graphics.setCursor(skill.getCursor());
                                GameState.userAction = skill.getUserAction();
                                GameState.nextBattleAction = skill.getAction().apply(creature, creatures, party.heroes);
                                GameState.uiStage.addAction(GameState.nextBattleAction);

                                // disable other skills
                                GameState.battleSkillIcons.get(creature).forEach(UIFactory.I::disableSkill);
                            }

                            return true;
                        }
                    });

                }
            }

            for (int j = skillsIndex; j < 3; j++) {
                battleScreen.add().size(16); // empty spaces
            }

            battleScreen.add().size(48, 16);

            if (i < party.heroes.size()) {
                Creature hero = party.heroes.get(i);
                skillsIndex = hero.skills.size();
                for (int j = 0; j < skillsIndex; j++) {

                    Skill skill = hero.skills.get(j);

                    AnimatedImage skillImage = new AnimatedImage(
                            new Animation<>(0.3f, TextureRegion.split(hero.skills.get(j).getIcon(), 16, 16)[0]));
                    battleScreen.add(skillImage).size(16).pad(5);
                    GameState.battleSkillIcons.computeIfAbsent(hero, value -> new ArrayList<>());
                    GameState.battleSkillIcons.get(hero).add(skillImage);

                    GameState.nextBattleAction = skill.getAction().apply(hero, party.heroes, creatures);
                }
            } else {
                skillsIndex = 0;
            }

            for (int j = skillsIndex; j < 3; j++) {
                battleScreen.add().size(16); // empty spaces
            }

            battleScreen.row();
        }

        GameState.uiStage.addActor(container);
        GameState.stage.addAction(new BattleFlowAction(creatures, party.heroes));
        GameState.isCombatSequence = true;
        UIState.battleSelectionCursor.toFront();
        container.toBack();
    }

}
