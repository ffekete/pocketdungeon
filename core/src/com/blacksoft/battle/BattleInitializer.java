package com.blacksoft.battle;

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
import com.blacksoft.battle.action.BattleFinishedAction;
import com.blacksoft.battle.action.BattleFlowAction;
import com.blacksoft.creature.Creature;
import com.blacksoft.creature.action.DamageSingleTargetAction;
import com.blacksoft.creature.action.RemoveFromBattleCheckerAction;
import com.blacksoft.hero.Party;
import com.blacksoft.screen.UIFactory;
import com.blacksoft.state.GameState;
import com.blacksoft.state.UIState;
import com.blacksoft.ui.AnimatedImage;
import com.blacksoft.user.actions.UserAction;

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
        //battleScreen.setPosition(50, 50);
        battleScreen.setSize(300, 200);

        GameState.stage.addAction(new BattleFinishedAction(party, creatures));

        GameState.uiStage.addActor(UIFactory.I.addMovingLabel("BATTLE"));
        GameState.uiStage.addActor(UIFactory.I.addMovingLabelShadow("BATTLE"));

        BattleSequence.creatures.clear();

        int index = Math.max(party.heroes.size(), creatures.size());
        index = Math.min(4, index);

        for (int i = 0; i < index; i++) {
            // CREATURE
            if (i < creatures.size()) {
                Creature creature = creatures.get(i);
                AnimatedImage creatureImage = new AnimatedImage(creatures.get(i).getAnimation());

                creatureImage.addAction(new RemoveFromBattleCheckerAction(creature, creatureImage));

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

                heroImage.addAction(new RemoveFromBattleCheckerAction(hero, heroImage));

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

                            // attack shift animation
                            SequenceAction attackAnimationAction = new SequenceAction();
                            attackAnimationAction.setActor(GameState.battleImages.get(GameState.battleSelectedCreature));
                            attackAnimationAction.addAction(Actions.moveBy(10, 0, 0.1f));
                            attackAnimationAction.addAction(Actions.moveBy(-10, 0, 0.1f));
                            GameState.battleImages.get(GameState.battleSelectedCreature).addAction(attackAnimationAction);
                        }

                        return true;
                    }
                });

            } else {
                battleScreen.add().size(48, 48).colspan(3);
            }
            battleScreen.row();

            // SKILLS
            int skillsIndex = 0;
            if (i < creatures.size()) {
                skillsIndex = creatures.get(i).skills.size();
                for (int j = 0; j < skillsIndex; j++) {
                    AnimatedImage animatedImage = new AnimatedImage(
                            new Animation<>(0.3f, TextureRegion.split(creatures.get(i).skills.get(j).getIcon(), 16, 16)[0]));
                    battleScreen.add(animatedImage).size(16).pad(5);

                    animatedImage.addListener(new InputListener() {
                        @Override
                        public void enter(InputEvent event,
                                          float x,
                                          float y,
                                          int pointer,
                                          Actor fromActor) {
                            animatedImage.setScale(1.25f);
                        }

                        @Override
                        public void exit(InputEvent event,
                                         float x,
                                         float y,
                                         int pointer,
                                         Actor toActor) {
                            animatedImage.setScale(1f);
                        }

                        @Override
                        public boolean touchDown(InputEvent event,
                                                 float x,
                                                 float y,
                                                 int pointer,
                                                 int button) {

                            GameState.userAction = UserAction.SelectSingleTarget;
                            GameState.nextBattleAction = new DamageSingleTargetAction(5);
                            GameState.uiStage.addAction(GameState.nextBattleAction);

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
                skillsIndex = party.heroes.get(i).skills.size();
                for (int j = 0; j < skillsIndex; j++) {
                    AnimatedImage animatedImage = new AnimatedImage(
                            new Animation<>(0.3f, TextureRegion.split(party.heroes.get(i).skills.get(j).getIcon(), 16, 16)[0]));
                    battleScreen.add(animatedImage).size(16).pad(5);
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
        container.toBack();
    }

}
