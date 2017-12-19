package com.ws.tictactoe.repo;


import com.ws.tictactoe.model.Game;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

@Repository
public class GameRepository {
    private NavigableMap<String, Game> games = new TreeMap<>();

    public Game create(Game game) {
        games.put(game.getId(),game);
        return game;
    }

    public Map.Entry<String, Game> getLatestGameEntry() {
        return games.lastEntry();
    }

    public Game findById(String id) {
        return games.get(id);
    }

    public Game delete(Game game) {
        return games.remove(game.getId());
    }
}
