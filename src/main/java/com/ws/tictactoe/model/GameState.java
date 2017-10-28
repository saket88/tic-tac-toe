package com.ws.tictactoe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;

@Builder
public class GameState {

    @Getter
    @JsonIgnore
    GameSign[][] board = new GameSign[3][3];


    @Getter
    Player nextPlayer;

    @Getter
    private GameSign winner;

    public void update(Cell cell) {
        GameSign[][] gameSign = getBoard();
        gameSign[cell.getRow()][cell.getColumn()]= getNextPlayer().getGameSign();
    }


}
