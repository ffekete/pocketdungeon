package com.blacksoft.hero.action;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.blacksoft.creature.Direction;
import com.blacksoft.creature.State;
import com.blacksoft.dungeon.Dungeon;
import com.blacksoft.dungeon.actions.TileTypeDetector;
import com.blacksoft.dungeon.objects.floor.Door;
import com.blacksoft.dungeon.objects.floor.DoorAbove;
import com.blacksoft.dungeon.objects.floor.TreasureChest;
import com.blacksoft.hero.Party;
import com.blacksoft.screen.input.KeyConfig;
import com.blacksoft.state.GameState;

public class MoveAndExplorePartyToTileAction extends MoveToAction {

    Vector2 targetNode;
    Vector2 previousNode = null;
    Party party;

    public MoveAndExplorePartyToTileAction(Party party,
                                           Vector2 targetNode) {
        super.actor = party;
        setPosition(targetNode.x, targetNode.y);
        setDuration(party.getSpeed());
        this.targetNode = targetNode;
        this.party = party;
    }

    @Override
    protected void begin() {
        super.begin();
        party.state = State.Walk;
    }

    @Override
    protected void end() {
        super.end();
        this.party.explored[(int) getX() / 16][(int) getY() / 16] = true;
        if ((Gdx.input.isKeyPressed(KeyConfig.LEFT)
                || Gdx.input.isKeyPressed(KeyConfig.RIGHT)
                || Gdx.input.isKeyPressed(KeyConfig.UP)
                || Gdx.input.isKeyPressed(KeyConfig.DOWN))) {
            party.state = State.WalkContinue;
        } else {
            party.state = State.Idle;
            party.direction = party.direction == Direction.Up || party.direction == Direction.Down ? Direction.Left : party.direction;
        }

        // interacting with items
        Party creature = (Party) actor;
        TileTypeDetector.getObjects(GameState.dungeon, Dungeon.DUNGEON_LAYER, (int) creature.targetNode.x, (int) creature.targetNode.y).forEach(o -> o.interact(GameState.party));

    }

    @Override
    public boolean act(float delta) {

        if (GameState.paused) {
            return false;
        }

        Party creature = (Party) actor;

        if (this.previousNode == null) {
            creature.previousNode = new Vector2(creature.getX() / 16, creature.getY() / 16);
            this.previousNode = creature.previousNode;
            creature.targetNode = new Vector2(targetNode.x / 16, targetNode.y / 16);
        }

        if (!TileTypeDetector.canTraverse(GameState.dungeon, (int) creature.targetNode.x, (int) creature.targetNode.y) || TileTypeDetector.hasAnyBlockingObjects(GameState.dungeon, Dungeon.DUNGEON_LAYER, (int) creature.targetNode.x, (int) creature.targetNode.y)) {
            creature.setPosition(previousNode.x * 16, previousNode.y * 16);
            creature.addAction(new ResetPartyActionsAction(creature));
            party.state = State.Idle;
            party.direction = party.direction == Direction.Up || party.direction == Direction.Down ? Direction.Left : party.direction;
            TileTypeDetector.getObjects(GameState.dungeon, Dungeon.DUNGEON_LAYER, (int) creature.targetNode.x, (int) creature.targetNode.y).forEach(o -> o.interact(GameState.party));
            return true;
        }

        boolean result = super.act(delta);
        return result;
    }
}
