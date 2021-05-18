package com.blacksoft.battle.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.blacksoft.battle.BattlePhase;
import com.blacksoft.battle.BattleSequence;
import com.blacksoft.creature.Creature;
import com.blacksoft.creature.action.RemoveFromBattleCheckerAction;
import com.blacksoft.hero.Hero;
import com.blacksoft.hero.action.RemoveFromPartyAction;
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

        if (GameState.battlePhase != BattlePhase.FinishTurn && GameState.battlePhase != BattlePhase.Prepare) {
            return false;
        }

        if (GameState.isCombatSequence && GameState.battleSelectedCreature == null) {
            GameState.battleSelectedCreature = BattleSequence.getNext();

            GameState.battlePhase = BattlePhase.StartTurn; // new turn

            SequenceAction sequenceAction = new SequenceAction();
            GameState.battleSelectedCreature.applyModifiers(sequenceAction);
            sequenceAction.addAction(new PrepareBattlePhaseFinishedAction());
            GameState.stage.addAction(sequenceAction);
        }

        if (GameState.battlePhase == BattlePhase.Prepare) {

            // if still alive after modifiers
            if (GameState.battleSelectedCreature.hp > 0) {

                GameState.battlePhase =  BattlePhase.Prepare_stg_2;

                UIState.battleSelectionCursor.setVisible(true);
                UIState.battleSelectionCursor.setPosition(GameState.battleImages.get(GameState.battleSelectedCreature).getX() + 90, GameState.battleImages.get(GameState.battleSelectedCreature).getY() + 60);
                UIState.battleSelectionCursor.setSize(48, 48);
                UIState.battleSelectionCursor.toFront();

                GameState.battleSkillIcons.values().stream().flatMap(Collection::stream)
                        .forEach(skillImage -> skillImage.setColor(1, 0, 0, 0.2f));

                GameState.battleSkillIcons.get(GameState.battleSelectedCreature).forEach(skillImage -> skillImage.setColor(1, 1, 1, 1f));

                if (heroes.contains(GameState.battleSelectedCreature)) {
                    // choose action, attack
                    Skill skill = GameState.battleSelectedCreature.skills.get(0); // todo improve skill selection

                    // pick a target
                    Optional<Creature> target = creatures.stream().filter(creature -> creature.hp > 0).findFirst();

                    target.ifPresent(creature -> {
                        GameState.nextAttackTarget = creature;
                        GameState.nextAttackTargetImage = GameState.battleImages.get(creature);

                        SequenceAction sequenceAction = new SequenceAction();
                        sequenceAction.addAction(Actions.delay(1f));
                        sequenceAction.addAction(skill.getAction().apply(GameState.battleSelectedCreature, creatures, heroes));

                        GameState.stage.addAction(sequenceAction);
                    });
                }
            } else {
                // selected creature is dead, pick the next one

                GameState.battlePhase = BattlePhase.Creature_Died_during_turn;

                SequenceAction sequenceAction = new SequenceAction();
                sequenceAction.addAction(Actions.delay(1.5f));
                sequenceAction.addAction(new RemoveFromBattleCheckerAction(GameState.battleSelectedCreature, GameState.battleImages.get(GameState.battleSelectedCreature)));

                if (Hero.class.isAssignableFrom(GameState.battleSelectedCreature.getClass())) {
                    sequenceAction.addAction(new RemoveFromPartyAction(GameState.battleSelectedCreature));
                }

                sequenceAction.addAction(new ClearBattleSelectedCreatureAction());
                sequenceAction.addAction(new ProgressBattleAction());
                GameState.stage.addAction(sequenceAction);

            }
        }

        return heroes.isEmpty() || creatures.isEmpty();
    }
}
