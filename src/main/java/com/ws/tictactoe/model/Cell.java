package com.ws.tictactoe.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class Cell {


    @Getter
    private final String id;

    @Getter
    private  final String tabId;

    @Getter
    private final int row;

    @Getter
    private final int column;


    @JsonCreator
    public Cell(@JsonProperty("row") int row, @JsonProperty("column") int column) {
        this.row = row;
        this.column = column;
        this.id = "";
        this.tabId="";
    }
    @JsonCreator
    public Cell(@JsonProperty("id") String id, @JsonProperty("tabId") String tabId, @JsonProperty("row") int row, @JsonProperty("column") int column) {
        this.tabId = tabId;
        this.row = row;
        this.column = column;
        this.id = id;
    }



}
