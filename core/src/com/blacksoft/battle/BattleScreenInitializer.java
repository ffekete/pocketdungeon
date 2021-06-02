package com.blacksoft.battle;

import com.badlogic.gdx.Gdx;
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

import static com.blacksoft.state.Config.BATTLE_SCREEN_POS_X;
import static com.blacksoft.state.Config.BATTLE_SCREEN_POS_Y;

public class BattleScreenInitializer {

    public static final int HERO_CELL_WIDTH = 32;

    public static void init(List<Creature> creatures,
                            Party party) {

        Container<Table> container = new Container<>();

        Table battleScreen = new Table();
        container.setActor(battleScreen);
        container.setPosition(BATTLE_SCREEN_POS_X, BATTLE_SCREEN_POS_Y);
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

                battleScreen.add(creatureImage).size(HERO_CELL_WIDTH, HERO_CELL_WIDTH).center().colspan(Config.MAX_SKILLS_SIZE);
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
                        UIState.selectionMarker.setPosition(creatureImage.getX() + BATTLE_SCREEN_POS_X + HERO_CELL_WIDTH / 3, creatureImage.getY() + BATTLE_SCREEN_POS_Y + HERO_CELL_WIDTH);
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
                battleScreen.add().size(HERO_CELL_WIDTH, HERO_CELL_WIDTH).colspan(Config.MAX_SKILLS_SIZE);
            }

            // SEPARATOR
            battleScreen.add().size(HERO_CELL_WIDTH, HERO_CELL_WIDTH);

            // HERO
            if (i < party.heroes.size()) {

                party.state = State.Idle;
                party.direction = Direction.Left;

                party.heroes.get(i).setState(State.Idle);
                party.heroes.get(i).direction = Direction.Left;

                AnimatedImage heroImage = new AnimatedImage(party.heroes.get(i).getAnimation());
                battleScreen.add(heroImage).size(HERO_CELL_WIDTH, HERO_CELL_WIDTH).center().colspan(Config.MAX_SKILLS_SIZE);
                party.heroes.get(i).setPosition(0, 0);

                Creature hero = party.heroes.get(i);
                BattleSequence.add(hero);

                GameState.battleImages.put(hero, heroImage);

            } else {
                battleScreen.add().size(HERO_CELL_WIDTH, HERO_CELL_WIDTH).colspan(Config.MAX_SKILLS_SIZE);
            }
            battleScreen.row();

            // hp bars for creatures
            if (i < creatures.size()) {
                Creature creature = creatures.get(i);
                GameState.battleHpAndMpProgressBars.computeIfAbsent(creature, value -> new ArrayList<>());

                DynamicProgressBar hpProgressBr = UIFactory.I.createHpProgressBar(creature, HERO_CELL_WIDTH, 5);
                battleScreen.add(hpProgressBr).size(HERO_CELL_WIDTH, 5).colspan(Config.MAX_SKILLS_SIZE).pad(1);

                GameState.battleHpAndMpProgressBars.get(creature).add(hpProgressBr);
            } else {
                // empty fillers
                battleScreen.add().size(HERO_CELL_WIDTH, 5).colspan(Config.MAX_SKILLS_SIZE);
            }

            battleScreen.add().size(HERO_CELL_WIDTH, 5);

            // hp bars for heroes
            if (i < party.heroes.size()) {
                Creature hero = party.heroes.get(i);
                GameState.battleHpAndMpProgressBars.computeIfAbsent(hero, value -> new ArrayList<>());

                DynamicProgressBar hpProgressBr = UIFactory.I.createHpProgressBar(hero, HERO_CELL_WIDTH, 5);
                battleScreen.add(hpProgressBr).size(HERO_CELL_WIDTH, 5).colspan(Config.MAX_SKILLS_SIZE).pad(1);

                GameState.battleHpAndMpProgressBars.get(hero).add(hpProgressBr);
            } else {
                // empty fillers
                battleScreen.add().size(HERO_CELL_WIDTH, 5).colspan(Config.MAX_SKILLS_SIZE);
            }

            battleScreen.row();

            // mp bars for creatures
            if (i < creatures.size()) {
                Creature creature = creatures.get(i);
                GameState.battleHpAndMpProgressBars.computeIfAbsent(creature, value -> new ArrayList<>());

                DynamicProgressBar mpProgressBar = UIFactory.I.createMpProgressBar(creature, HERO_CELL_WIDTH, 5);
                battleScreen.add(mpProgressBar).size(HERO_CELL_WIDTH, 5).colspan(Config.MAX_SKILLS_SIZE).pad(1);

                GameState.battleHpAndMpProgressBars.get(creature).add(mpProgressBar);
            } else {
                // empty fillers
                battleScreen.add().size(HERO_CELL_WIDTH, 5).colspan(Config.MAX_SKILLS_SIZE);
            }

            battleScreen.add().size(HERO_CELL_WIDTH, 5);

            // mp and mp bars for heroes
            if (i < party.heroes.size()) {
                Creature hero = party.heroes.get(i);
                GameState.battleHpAndMpProgressBars.computeIfAbsent(hero, value -> new ArrayList<>());

                DynamicProgressBar mpProgressBar = UIFactory.I.createMpProgressBar(hero, HERO_CELL_WIDTH, 5);
                battleScreen.add(mpProgressBar).size(HERO_CELL_WIDTH, 5).colspan(Config.MAX_SKILLS_SIZE).pad(1);

                GameState.battleHpAndMpProgressBars.get(hero).add(mpProgressBar);
            } else {
                // empty fillers
                battleScreen.add().size(HERO_CELL_WIDTH, 5).colspan(Config.MAX_SKILLS_SIZE);
            }

            battleScreen.row();

            // SKILLS
            int skillsIndex = 0;
            if (i < creatures.size()) {

                Creature creature = creatures.get(i);

                skillsIndex = creature.skills.size();
                for (int j = 0; j < skillsIndex; j++) {

                    Skill skill = creature.skills.get(j);

                    AnimatedImage skillImage = UIFactory.createSkillIcon(creature, skill);

                    battleScreen.add(skillImage).size(HERO_CELL_WIDTH / Config.MAX_SKILLS_SIZE - 2).pad(1);
                }
            }

            for (int j = skillsIndex; j < Config.MAX_SKILLS_SIZE; j++) {
                battleScreen.add().size(HERO_CELL_WIDTH / Config.MAX_SKILLS_SIZE); // empty spaces
            }

            battleScreen.add().size(HERO_CELL_WIDTH, 16);

            if (i < party.heroes.size()) {
                Creature hero = party.heroes.get(i);
                skillsIndex = hero.skills.size();
                for (int j = 0; j < skillsIndex; j++) {

                    Skill skill = hero.skills.get(j);

                    AnimatedImage skillImage = UIFactory.createSkillIcon(hero, skill);

                    battleScreen.add(skillImage).size(HERO_CELL_WIDTH / Config.MAX_SKILLS_SIZE - 2).pad(1);

                    // if the skill is a group (e.g. spellGroup do not add click actions
                    if (skill.getSubmenu() != null) {

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
                            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                                SpellScreenInitializer.init(hero, creatures);
                                return true;
                            }
                        });

                    } else {
                        addSkillAction(creatures, party, hero, skill, skillImage);
                        GameState.nextBattleAction = skill.getAction().apply(hero, party.heroes, creatures);
                    }
                }
            } else {
                skillsIndex = 0;
            }

            for (int j = skillsIndex; j < Config.MAX_SKILLS_SIZE; j++) {
                battleScreen.add().size(HERO_CELL_WIDTH / Config.MAX_SKILLS_SIZE); // empty spaces
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

    public static void addSkillAction(List<Creature> creatures, Party party, Creature hero, Skill skill, Actor skillImage) {
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

}
