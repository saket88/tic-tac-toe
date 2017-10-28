package com.ws.tictactoe.model;


public enum GameSign {
    X,O,Blank;

    public GameSign toggle() {
        return this.equals(GameSign.X) ? GameSign.O :GameSign.X;
    }
}
