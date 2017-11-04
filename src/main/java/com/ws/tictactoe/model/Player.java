package com.ws.tictactoe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
public class Player {

    @Getter
    private final GameSign gameSign;


    public Player(String gameSign) {
        this.gameSign = GameSign.valueOf(gameSign);
    }


}
