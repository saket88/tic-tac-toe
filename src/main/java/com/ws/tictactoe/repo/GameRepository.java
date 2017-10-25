package com.ws.tictactoe.repo;


import com.ws.tictactoe.model.Game;
import org.springframework.stereotype.Repository;

@Repository
public class GameRepository {
    public Game save(Game game) {
        return game;
    }
}
