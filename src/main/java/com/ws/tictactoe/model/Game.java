package com.ws.tictactoe.model;

public class Game {
    private GameState state;

    public Game(GameState gameState) {
        this.state=gameState;
    }

    public GameState getState() {
        return this.state;
    }
}
