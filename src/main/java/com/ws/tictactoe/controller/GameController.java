package com.ws.tictactoe.controller;

import com.ws.tictactoe.generator.GameFactory;
import com.ws.tictactoe.model.Game;
import com.ws.tictactoe.model.GameDTO;
import com.ws.tictactoe.model.GameParams;
import com.ws.tictactoe.repo.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/games")
public class GameController {

    private GameFactory gameFactory;
    private GameRepository gameRepository;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public GameDTO create(@RequestBody @Validated GameParams gameParams) {
        Game game = gameFactory.createGame(gameParams);
        game = gameRepository.save(game);
        return new GameDTO(game);
    }

    @Autowired
    public void setGameRepository(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Autowired
    public void setGameFactory(GameFactory gameFactory) {
        this.gameFactory = gameFactory;
    }


}
