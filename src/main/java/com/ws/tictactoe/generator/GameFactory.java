package com.ws.tictactoe.generator;

import com.ws.tictactoe.model.Game;
import com.ws.tictactoe.model.GameParams;
import com.ws.tictactoe.model.GameState;
import org.springframework.stereotype.Service;

@Service
public class GameFactory {

    public Game createGame(GameParams gameParams) {
        GameState gameState = GameState.builder()
                .nextPlayer(gameParams.getFirstPlayer())
                .build();
        return new Game(gameState);
    }
}
