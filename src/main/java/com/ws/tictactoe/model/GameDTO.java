package com.ws.tictactoe.model;

import lombok.Getter;

public class GameDTO {

    private final Game game;
    @Getter
    private final Player nextPlayer;
    @Getter
    private final String id;

    public GameDTO(){
        game = null;
        nextPlayer = null;
        id = "";
    }


    public GameDTO(Game game) {
        this.game = game;
        this.nextPlayer = game.getState().getNextPlayer();
        this.id = game.getId();
    }



}
