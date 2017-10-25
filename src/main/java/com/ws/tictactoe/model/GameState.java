package com.ws.tictactoe.model;

import lombok.Builder;
import lombok.Getter;

@Builder
public class GameState {

    @Getter
    Player nextPlayer;

}
