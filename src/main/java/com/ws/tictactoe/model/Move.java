package com.ws.tictactoe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Move {


    @Getter
    private final Cell cell;
    @Getter
    private final GameSign piece;

    public Move(GameSign piece, Cell cell) {
        this.piece = piece;
        this.cell = cell;
    }
}
