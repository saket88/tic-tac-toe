package com.ws.tictactoe.model;

import lombok.Getter;

public class Player {

    @Getter
    private final GameSign gameSign;

    public Player(String gameSign) {
        this.gameSign = GameSign.valueOf(gameSign);
    }
}
