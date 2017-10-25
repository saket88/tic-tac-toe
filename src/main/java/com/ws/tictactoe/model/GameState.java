package com.ws.tictactoe.model;

import lombok.Builder;

@Builder
public class GameState {

    Player nextPlayer;

    public Player getNextPlayer() {
        return nextPlayer;
    }
}
