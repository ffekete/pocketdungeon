package com.blacksoft.battle.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.actions.VisibleAction;
import com.blacksoft.battle.BattleSequence;
import com.blacksoft.creature.Creature;
import com.blacksoft.creature.action.DamageSingleTargetAction;
import com.blacksoft.skill.MeleeAttack;
import com.blacksoft.skill.Skill;
import com.blacksoft.state.GameState;
import com.blacksoft.state.UIState;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class BattleFlowAction extends Action {

    private List<Creature> creatures;
    private List<Creature> heroes;

    public BattleFlowAction(List<Creature> creatures,
                            List<Creature> heroes) {
        this.creatures = creatures;
        this.heroes = heroes;
    }

    @Override
    public boolean act(float delta) {

        if (!GameState.isCombatSequence) {
            return true;
        }

        if (GameState.isCombatSequence && GameState.battleSelectedCreature == null) {
            GameState.battleSelectedCreature = BattleSequence.getNext();

            UIState.battleSelectionCursor.setVisible(true);
            UIState.battleSelectionCursor.setPosition(GameState.battleImages.get(GameState.battleSelectedCreature).getX() + 90, GameState.battleImages.get(GameState.battleSelectedCreature).getY() + 60);
            UIState.battleSelectionCursor.setSize(48, 48);
            UIState.battleSelectionCursor.toFront();

            GameState.battleSkillIcons.values().stream().flatMap(Collection::stream)
                    .forEach(skillImage -> skillImage.setColor(1, 0, 0, 0.2f));

            GameState.battleSkillIcons.get(GameState.battleSelectedCreature).forEach(skillImage -> skillImage.setColor(0, 1, 0, 1f));

            if (heroes.contains(GameState.battleSelectedCreature)) {
                // choose action, attack
                Skill skill = GameState.battleSelectedCreature.skills.get(0); // todo improve skill selection

                skill.act(creatures, heroes);

            } else {
                // player turn
            }

        }

        return heroes.isEmpty() || creatures.isEmpty();
    }
}
