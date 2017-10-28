package com.ws.tictactoe.model;

import java.util.UUID;

public class Game {
    private final GameState state;
    private final String id = UUID.randomUUID().toString();

    public Game(GameState gameState) {
        this.state=gameState;
    }

    public GameState getState() {
        return this.state;
    }

    public String getId() {
        return id;
    }
}
