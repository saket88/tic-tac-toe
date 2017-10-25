package com.ws.tictactoe.generator;

import com.ws.tictactoe.model.Game;
import org.springframework.stereotype.Service;

@Service
public class GameFactory {
    public Game createGame(String gameParams) {

        return new Game();
    }
}
