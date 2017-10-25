package com.ws.tictactoe.generator;

import com.ws.tictactoe.model.Game;
import com.ws.tictactoe.model.GameParams;
import org.springframework.stereotype.Service;

@Service
public class GameFactory {
    public Game createGame(GameParams gameParams) {

        return new Game();
    }
}
