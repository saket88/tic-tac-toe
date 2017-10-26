package com.ws.tictactoe.model;

public class GameDTO {

    private final Game game;
    private final Player nextPlayer;

    public GameDTO(){
        game = null;
        nextPlayer = null;
    }


    public GameDTO(Game game) {
        this.game = game;
        this.nextPlayer = game.getState().getNextPlayer();
    }

    public String getId() {
        return game.getId();
    }


    public Player getNextPlayer() {
        return this.nextPlayer;
    }


}
