package com.blacksoft.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.blacksoft.battle.action.BattleFlowAction;
import com.blacksoft.battle.action.MoveBattleToFinishTurnAction;
import com.blacksoft.battle.action.ResetPartyStatusAction;
import com.blacksoft.creature.Creature;
import com.blacksoft.creature.Direction;
import com.blacksoft.creature.State;
import com.blacksoft.hero.Party;
import com.blacksoft.hero.action.ResetPartyActionsAction;
import com.blacksoft.screen.UIFactory;
import com.blacksoft.skill.Skill;
import com.blacksoft.state.Config;
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

        GameState.party.direction = Direction.Left;
        GameState.party.state = State.Idle;
        GameState.party.heroes.forEach(hero -> {
            hero.direction = Direction.Left;
            hero.setState(State.Idle);
        });

        GameState.creaturesInvolvedInBattle = creatures;

        battleScreen.background(new TextureRegionDrawable(UIState.battleScreenBackground));
        battleScreen.setSize(300, 200);

        GameState.uiStage.addActor(UIFactory.I.addMovingLabelShadow("BATTLE"));
        GameState.uiStage.addActor(UIFactory.I.addMovingLabel("BATTLE"));

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
                creature.setState(State.Idle);
                creature.direction = Direction.Right;
                AnimatedImage creatureImage = new AnimatedImage(creatures.get(i).getAnimation());

                battleScreen.add(creatureImage).size(48, 48).center().colspan(Config.MAX_SKILLS_SIZE);
                GameState.battleImages.put(creature, creatureImage);
                BattleSequence.add(creature);

                creatureImage.addListener(new InputListener() {

                    @Override
                    public void enter(InputEvent event,
                                      float x,
                                      float y,
                                      int pointer,
                                      Actor fromActor) {
                        UIState.selectionMarker.setVisible(true);
                        UIState.selectionMarker.toFront();
                        UIState.selectionMarker.setPosition(creatureImage.getX() + 90 + 16, creatureImage.getY() + 60 + 48);
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
                            GameState.nextAttackTarget = creature;
                            GameState.nextAttackTargetImage = creatureImage;
                            Gdx.graphics.setCursor(UIState.defaultCursor);
                        }

                        return true;
                    }
                });
            } else {
                battleScreen.add().size(48, 48).colspan(Config.MAX_SKILLS_SIZE);
            }

            // SEPARATOR
            battleScreen.add().size(48, 48);

            // HERO
            if (i < party.heroes.size()) {

                party.state = State.Idle;
                party.direction = Direction.Left;

                party.heroes.get(i).setState(State.Idle);
                party.heroes.get(i).direction = Direction.Left;

                AnimatedImage heroImage = new AnimatedImage(party.heroes.get(i).getAnimation());
                battleScreen.add(heroImage).size(48, 48).center().colspan(Config.MAX_SKILLS_SIZE);
                party.heroes.get(i).setPosition(0, 0);

                Creature hero = party.heroes.get(i);
                BattleSequence.add(hero);

                GameState.battleImages.put(hero, heroImage);

            } else {
                battleScreen.add().size(48, 48).colspan(Config.MAX_SKILLS_SIZE);
            }
            battleScreen.row();

            // hp bars for creatures
            if (i < creatures.size()) {
                Creature creature = creatures.get(i);
                GameState.battleHpAndMpProgressBars.computeIfAbsent(creature, value -> new ArrayList<>());

                DynamicProgressBar hpProgressBr = UIFactory.I.createHpProgressBar(creature, 48, 5);
                battleScreen.add(hpProgressBr).size(48, 5).colspan(Config.MAX_SKILLS_SIZE).pad(1);

                GameState.battleHpAndMpProgressBars.get(creature).add(hpProgressBr);
            } else {
                // empty fillers
                battleScreen.add().size(48, 5).colspan(Config.MAX_SKILLS_SIZE);
            }

            battleScreen.add().size(48, 5);

            // hp bars for heroes
            if (i < party.heroes.size()) {
                Creature hero = party.heroes.get(i);
                GameState.battleHpAndMpProgressBars.computeIfAbsent(hero, value -> new ArrayList<>());

                DynamicProgressBar hpProgressBr = UIFactory.I.createHpProgressBar(hero, 48, 5);
                battleScreen.add(hpProgressBr).size(48, 5).colspan(Config.MAX_SKILLS_SIZE).pad(1);

                GameState.battleHpAndMpProgressBars.get(hero).add(hpProgressBr);
            } else {
                // empty fillers
                battleScreen.add().size(48, 5).colspan(Config.MAX_SKILLS_SIZE);
            }

            battleScreen.row();

            // mp bars for creatures
            if (i < creatures.size()) {
                Creature creature = creatures.get(i);
                GameState.battleHpAndMpProgressBars.computeIfAbsent(creature, value -> new ArrayList<>());

                DynamicProgressBar mpProgressBar = UIFactory.I.createMpProgressBar(creature, 48, 5);
                battleScreen.add(mpProgressBar).size(48, 5).colspan(Config.MAX_SKILLS_SIZE).pad(1);

                GameState.battleHpAndMpProgressBars.get(creature).add(mpProgressBar);
            } else {
                // empty fillers
                battleScreen.add().size(48, 5).colspan(Config.MAX_SKILLS_SIZE);
            }

            battleScreen.add().size(48, 5);

            // mp and mp bars for heroes
            if (i < party.heroes.size()) {
                Creature hero = party.heroes.get(i);
                GameState.battleHpAndMpProgressBars.computeIfAbsent(hero, value -> new ArrayList<>());

                DynamicProgressBar mpProgressBar = UIFactory.I.createMpProgressBar(hero, 48, 5);
                battleScreen.add(mpProgressBar).size(48, 5).colspan(Config.MAX_SKILLS_SIZE).pad(1);

                GameState.battleHpAndMpProgressBars.get(hero).add(mpProgressBar);
            } else {
                // empty fillers
                battleScreen.add().size(48, 5).colspan(Config.MAX_SKILLS_SIZE);
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
                    battleScreen.add(skillImage).size(10).pad(1);
                    UIFactory.I.disableSkill(skillImage);

                    GameState.battleSkillIcons.computeIfAbsent(creature, value -> new ArrayList<>());
                    GameState.battleSkillIcons.get(creature).add(skillImage);
                }
            }

            for (int j = skillsIndex; j < 4; j++) {
                battleScreen.add().size(12); // empty spaces
            }

            battleScreen.add().size(48, 16);

            if (i < party.heroes.size()) {
                Creature hero = party.heroes.get(i);
                skillsIndex = hero.skills.size();
                for (int j = 0; j < skillsIndex; j++) {

                    Skill skill = hero.skills.get(j);

                    AnimatedImage skillImage = new AnimatedImage(
                            new Animation<>(0.3f, TextureRegion.split(hero.skills.get(j).getIcon(), 16, 16)[0]));
                    battleScreen.add(skillImage).size(10).pad(1);

                    GameState.battleSkillIcons.computeIfAbsent(hero, value -> new ArrayList<>());
                    GameState.battleSkillIcons.get(hero).add(skillImage);

                    GameState.nextBattleAction = skill.getAction().apply(hero, party.heroes, creatures);

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

                            if (GameState.userAction == UserAction.Idle && GameState.battlePhase == BattlePhase.Prepare_stg_2) {
                                Gdx.graphics.setCursor(skill.getCursor());
                                GameState.userAction = skill.getUserAction();
                                GameState.nextBattleAction = skill.getAction().apply(hero, creatures, party.heroes);
                                GameState.uiStage.addAction(GameState.nextBattleAction);

                                // disable other skills
                                GameState.battleSkillIcons.get(hero).forEach(UIFactory.I::disableSkill);
                            }

                            return true;
                        }
                    });
                }
            } else {
                skillsIndex = 0;
            }

            for (int j = skillsIndex; j < 4; j++) {
                battleScreen.add().size(12); // empty spaces
            }

            battleScreen.row();
        }

        GameState.uiStage.addActor(container);

        SequenceAction startBattleAction = new SequenceAction();

        GameState.battlePhase = BattlePhase.NotStartedYet;

        startBattleAction.addAction(new ResetPartyStatusAction());
        startBattleAction.addAction(Actions.delay(4f));
        startBattleAction.addAction(new MoveBattleToFinishTurnAction());
        startBattleAction.addAction(new BattleFlowAction(creatures, party.heroes));

        GameState.stage.addAction(startBattleAction);
        GameState.isCombatSequence = true;
        UIState.battleSelectionCursor.toFront();
        container.toBack();
    }

}
