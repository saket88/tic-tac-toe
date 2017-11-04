package com.ws.tictactoe.repo;


import com.ws.tictactoe.model.Game;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

@Repository
public class GameRepository {
    private ConcurrentHashMap<String, Game> games = new ConcurrentHashMap<>();

    public Game save(Game game) {
        games.put(game.getId(),game);
        return game;
    }

    public Game findById(String id) {
        return games.get(id);
    }

    public Game delete(Game game) {
        return games.remove(game.getId());
    }
}
