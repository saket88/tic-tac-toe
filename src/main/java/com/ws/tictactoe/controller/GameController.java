package com.ws.tictactoe.controller;

import com.ws.tictactoe.generator.GameFactory;
import com.ws.tictactoe.model.*;
import com.ws.tictactoe.repo.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @RequestMapping(value = "/{id}/turn",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public GameDTO playTurn(@RequestBody @Validated TurnParams turnParams,
                            @PathVariable String id) {
        Game game = gameRepository.findById(id);
        game.playTurn(turnParams);
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
