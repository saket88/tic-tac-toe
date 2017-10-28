package com.ws.tictactoe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;

@Builder
public class GameState {

    @Getter
    @JsonIgnore
    GameSign[][] board;


    @Getter
    Player nextPlayer;

    @Getter
    private GameSign winner;

    public void update(Cell cell) {
        initialiazeGameBoard();
        GameSign[][] gameSigns = getBoard();
        gameSigns[cell.getRow()][cell.getColumn()]= getNextPlayer().getGameSign();
        nextPlayer=new Player(nextPlayer.getGameSign().toggle().name());
    }

    private void initialiazeGameBoard() {
        if(board==null)
        board= new GameSign[][]{
                {GameSign.Blank, GameSign.Blank, GameSign.Blank},
                {GameSign.Blank, GameSign.Blank, GameSign.Blank},
                {GameSign.Blank, GameSign.Blank, GameSign.Blank}

        };
    }


}
