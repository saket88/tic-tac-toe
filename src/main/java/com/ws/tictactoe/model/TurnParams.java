package com.ws.tictactoe.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TurnParams {
    public final Cell move;

    @JsonCreator
    public TurnParams( @JsonProperty("move") Cell move) {
        this.move = move;
    }

    public Cell getMove() {
        return move;
    }
}
