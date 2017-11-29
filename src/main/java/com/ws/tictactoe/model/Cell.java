package com.ws.tictactoe.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class Cell {


    @Getter
    public final String id;

    @Getter
    public final int row;

    @Getter
    public final int column;


    @JsonCreator
    public Cell(@JsonProperty("row") int row, @JsonProperty("column") int column) {
        this.row = row;
        this.column = column;
        this.id = "";
    }
    @JsonCreator
    public Cell( @JsonProperty("id") String id,@JsonProperty("row") int row, @JsonProperty("column") int column) {
        this.row = row;
        this.column = column;
        this.id = id;
    }



}
