package com.ws.tictactoe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
public class Player {


    @Getter
    private Cell latestMove;

    @Getter
    private final GameSign gameSign;


    public Player(String gameSign) {
        this.gameSign = GameSign.valueOf(gameSign);
    }

    public Player(String gameSign,Cell latestMove) {
        this.gameSign = GameSign.valueOf(gameSign);
        this.latestMove=latestMove;
    }


}
