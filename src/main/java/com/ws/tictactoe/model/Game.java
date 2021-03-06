package com.ws.tictactoe.model;

import lombok.Getter;

import java.util.UUID;


public class Game {

    @Getter
    private  final GameState state;

    @Getter
    private final String id = UUID.randomUUID().toString();

    public Game(){

        state = null;
    }
    public Game(GameState gameState) {
        this.state=gameState;
    }

    public void playTurn(TurnParams turnParams) {
        state.update(turnParams.getMove());
    }

    public Move getLastMove() {
        return state.getLastMove();
    }
}
