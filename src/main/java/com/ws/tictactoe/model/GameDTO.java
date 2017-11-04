package com.ws.tictactoe.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE,getterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY)
public class GameDTO {


    public final Game game;

    @Getter
    private  Player nextPlayer;

    @Getter
    private  String id;

    @Getter
    private  Move move;

    public GameSign[][] getBoard() {
        board = new GameSign[][]{
                {GameSign.Blank, GameSign.Blank, GameSign.Blank},
                {GameSign.Blank, GameSign.Blank, GameSign.Blank},
                {GameSign.Blank, GameSign.Blank, GameSign.Blank}

        };

        return board;
    }

    public boolean isGameEnded() {
        return game.getState().isAtEnd();
    }

    private GameSign[] [] board;

    public GameDTO(){
        game = null;
    }
    
    public GameDTO(Game game)
    {
        this.game=game;
        this.nextPlayer = game.getState().getNextPlayer();
        this.id=game.getId();
        this.move=game.getLastMove();
    }


    public GameSign getWinner() {
        return game.getState().getWinner();
    }

    public WinningPosition getWinningSequence() {
        return game.getState().getWinningSequence();
    }


}
