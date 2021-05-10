package com.blacksoft;

import com.blacksoft.dungeon.actions.AbstractAction;
import com.blacksoft.dungeon.actions.ActionLevel;
import com.blacksoft.state.GameState;
import com.google.gson.Gson;

import java.util.*;

public class ActionsHandler {

    public static final ActionsHandler I = new ActionsHandler();

    public static final Gson GSON = new Gson();

    Map<ActionLevel, List<AbstractAction>> actions = new HashMap<>();

    public AbstractAction draw(ActionLevel actionLevel) {
        if(!actions.get(actionLevel).isEmpty()) {
            return clone(actions.get(actionLevel).get(new Random().nextInt(actions.get(actionLevel).size())));
        }

        return null;
    }

    public AbstractAction clone(AbstractAction abstractAction) {
        return GSON.fromJson(GSON.toJson(abstractAction), abstractAction.getClass());
    }

    private List<AbstractAction> getActionOfType(ActionLevel actionLevel) {
        List<AbstractAction> actions = new ArrayList<>();
        GameState.unlockedActionsPrioritized.stream()
                .filter(action -> action.getActionLevel().equals(actionLevel))
                .forEach(abstractAction -> {
                    for (int i = 0; i < abstractAction.getPriority(); i++) {
                        actions.add(abstractAction);
                    }
                });

        return actions;
    }

    public void initActions() {
        for (AbstractAction unlockedAction : GameState.unlockedActions) {
            for (int j = 0; j < unlockedAction.getPriority(); j++) {
                GameState.unlockedActionsPrioritized.add(unlockedAction);
            }
        }

        actions.put(ActionLevel.Basic, getActionOfType(ActionLevel.Basic));
        actions.put(ActionLevel.Advanced, getActionOfType(ActionLevel.Advanced));
        actions.put(ActionLevel.Expert, getActionOfType(ActionLevel.Expert));
        actions.put(ActionLevel.Master, getActionOfType(ActionLevel.Master));
        actions.put(ActionLevel.Unique, getActionOfType(ActionLevel.Unique));
    }

}
