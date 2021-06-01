package com.blacksoft.screen.action;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.blacksoft.creature.Direction;
import com.blacksoft.creature.State;
import com.blacksoft.dungeon.actions.TileTypeDetector;
import com.blacksoft.hero.Party;
import com.blacksoft.hero.action.MoveAndExplorePartyToTileAction;
import com.blacksoft.screen.input.KeyConfig;
import com.blacksoft.state.GameState;

public class KeyPressedAction extends Action {


    @Override
    public boolean act(float delta) {

        Party party = GameState.party;

        if (Gdx.input.isKeyPressed(KeyConfig.LEFT)) {
            if (party.state == State.Idle || party.state == State.WalkContinue) {
                party.direction = Direction.Left;

                if (TileTypeDetector.canTraverse(GameState.dungeon, (int) party.getX() / 16 - 1, (int) party.getY() / 16)) {
                    MoveAndExplorePartyToTileAction moveToTileAction = new MoveAndExplorePartyToTileAction(party, new Vector2(party.getX() - 16, party.getY()));
                    GameState.party.addAction(moveToTileAction);
                } else {
                    party.state = State.Idle;
                    party.direction = party.direction == Direction.Up || party.direction == Direction.Down ? Direction.Left : party.direction;
                }
            }
        }

        if (Gdx.input.isKeyPressed(KeyConfig.DOWN)) {
            if (party.state == State.Idle || party.state == State.WalkContinue) {
                party.direction = Direction.Down;
                if (TileTypeDetector.canTraverse(GameState.dungeon, (int) party.getX() / 16, (int) party.getY() / 16 - 1)) {
                    MoveAndExplorePartyToTileAction moveToTileAction = new MoveAndExplorePartyToTileAction(party, new Vector2(party.getX(), party.getY() - 16));
                    GameState.party.addAction(moveToTileAction);
                } else {
                    party.state = State.Idle;
                    party.direction = party.direction == Direction.Up || party.direction == Direction.Down ? Direction.Left : party.direction;
                }
            }
        }

        if (Gdx.input.isKeyPressed(KeyConfig.RIGHT)) {
            if (party.state == State.Idle || party.state == State.WalkContinue) {
                party.direction = Direction.Right;
                if (TileTypeDetector.canTraverse(GameState.dungeon, (int) party.getX() / 16 + 1, (int) party.getY() / 16)) {
                    MoveAndExplorePartyToTileAction moveToTileAction = new MoveAndExplorePartyToTileAction(party, new Vector2(party.getX() + 16, party.getY()));
                    GameState.party.addAction(moveToTileAction);
                } else {
                    party.state = State.Idle;
                    party.direction = party.direction == Direction.Up || party.direction == Direction.Down ? Direction.Left : party.direction;
                }
            }
        }

        if (Gdx.input.isKeyPressed(KeyConfig.UP)) {
            if (party.state == State.Idle || party.state == State.WalkContinue) {
                party.direction = Direction.Up;
                if (TileTypeDetector.canTraverse(GameState.dungeon, (int) party.getX() / 16, (int) party.getY() / 16 + 1)) {
                    MoveAndExplorePartyToTileAction moveToTileAction = new MoveAndExplorePartyToTileAction(party, new Vector2(party.getX(), party.getY() + 16));
                    GameState.party.addAction(moveToTileAction);
                } else {
                    party.state = State.Idle;
                    party.direction = party.direction == Direction.Up || party.direction == Direction.Down ? Direction.Left : party.direction;
                }
            }
        }

        if (Gdx.input.isKeyPressed(KeyConfig.DOWN)) {
            if (party.state == State.Idle || party.state == State.WalkContinue) {
                party.direction = Direction.Down;
                if (TileTypeDetector.canTraverse(GameState.dungeon, (int) party.getX() / 16, (int) party.getY() / 16 - 1)) {
                    MoveAndExplorePartyToTileAction moveToTileAction = new MoveAndExplorePartyToTileAction(party, new Vector2(party.getX(), party.getY() - 16));
                    GameState.party.addAction(moveToTileAction);
                } else {
                    party.state = State.Idle;
                    party.direction = party.direction == Direction.Up || party.direction == Direction.Down ? Direction.Left : party.direction;
                }
            }
        }

        return false;
    }
}
