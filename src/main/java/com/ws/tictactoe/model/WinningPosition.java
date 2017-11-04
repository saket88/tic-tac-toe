package com.ws.tictactoe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class WinningPosition {

    @Getter
    private Cell start;

    @Getter
    private Cell end;


}
