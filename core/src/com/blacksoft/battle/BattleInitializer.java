package com.blacksoft.battle;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
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


        GameState.uiStage.addActor(UIFactory.I.addMovingLabel("BATTLE"));
        GameState.uiStage.addActor(UIFactory.I.addMovingLabelShadow("BATTLE"));
        battleScreen.debugAll();

        int index = Math.max(party.heroes.size(), creatures.size());
        index = Math.min(4, index);

        for (int i = 0; i < index; i++) {
            // CREATURE
            if (i < creatures.size()) {
                battleScreen.add(new AnimatedImage(creatures.get(i).getAnimation())).size(48, 48).center().colspan(3);
                creatures.get(i).setPosition(0, 0);
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
                            heroImage.addAction(new RemoveFromBattleCheckerAction(GameState.nextAttackTarget, GameState.nextAttackTargetImage));
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
        container.toBack();
    }

}
