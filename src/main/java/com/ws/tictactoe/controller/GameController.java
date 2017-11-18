package com.ws.tictactoe.controller;


import com.ws.tictactoe.model.Cell;
import com.ws.tictactoe.model.Game;
import com.ws.tictactoe.model.Player;
import com.ws.tictactoe.repo.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/games")
public class GameController {


    private GameRepository gameRepository;


    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Game create(@RequestBody @Validated Player player) {

        Game game = Game.builder()
                .nextPlayer(player)
                .build();

        return gameRepository.save(game);
    }

    @RequestMapping(value = "/{id}/turn",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Game playTurn(@RequestBody @Validated Cell cell,
                            @PathVariable String id) {
        Game game = gameRepository.findById(id);
        game.playTurn(cell);
        return gameRepository.save(game);
    }


    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
            Game game = gameRepository.findById(id);
            gameRepository.delete(game);
    }

    @Autowired
    public void setGameRepository(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }




}
