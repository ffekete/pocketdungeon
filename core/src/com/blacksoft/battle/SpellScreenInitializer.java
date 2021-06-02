package com.blacksoft.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.blacksoft.creature.Creature;
import com.blacksoft.screen.UIFactory;
import com.blacksoft.skill.Skill;
import com.blacksoft.skill.SpellBook;
import com.blacksoft.state.GameState;
import com.blacksoft.ui.AnimatedImage;

import java.util.List;

import static com.blacksoft.battle.BattleScreenInitializer.addSkillAction;
import static com.blacksoft.state.Config.BATTLE_SCREEN_POS_X;
import static com.blacksoft.state.Config.BATTLE_SCREEN_POS_Y;

public class SpellScreenInitializer {

    public static final int ICON_SIZE = 32;
    private static TextureRegion spellScreenBackground = new TextureRegion(new Texture(Gdx.files.internal("ui/SpellScreen.png")));

    public static void init(Creature hero, List<Creature> creatures) {

        Table spellScreen = new Table();
        spellScreen.background(new TextureRegionDrawable(spellScreenBackground));
        spellScreen.setPosition(BATTLE_SCREEN_POS_X, BATTLE_SCREEN_POS_Y);
        spellScreen.setSize(300, 200);

        // Add left page
        Table leftPage = new Table();
        Container<Table> leftPageContainer = new Container<>(leftPage);
        leftPageContainer.setSize(150, 200);
        leftPage.setFillParent(true);
        spellScreen.add(leftPageContainer).pad(5, 5, 5, 5);

        int colCount = 0;

        int rowCount = 0;

        for (Skill skill : hero.skills) {

            if (SpellBook.class.isAssignableFrom(skill.getClass())) {

                SpellBook spellBook = (SpellBook) skill;

                for (Skill spell : spellBook.spells) {

                    AnimatedImage animatedImage = UIFactory.createSkillIcon(hero, spell);
                    UIFactory.I.enableSkill(animatedImage);
                    leftPage.add(animatedImage).size(32).pad(4);

                    addSkillAction(creatures, GameState.party, hero, spell, animatedImage);
                    animatedImage.addListener(new InputListener() {
                        @Override
                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                            GameState.uiStage.getActors().removeValue(spellScreen, true);
                            return true;
                        }
                    });

                    colCount++;

                    if(colCount == 3) {
                        leftPage.add().size(28, 32);
                    }

                    if (colCount == 6) {
                        leftPage.row();
                        colCount = 0;
                        rowCount++;
                    }
                }
            }
        }

        // fill thee remaining items in the last used row
        for (int j = colCount; j < 6; j++) {
            leftPage.add().size(32).pad(4);
        }
        leftPage.row();

        // then fill all
        for(int i = rowCount; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                leftPage.add().size(32).pad(4);
                if(j == 3) {
                    leftPage.add().size(28, 32);
                }
            }
            leftPage.row();
        }

        GameState.uiStage.addActor(spellScreen);
    }

}
