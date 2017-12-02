package com.ws.tictactoe.controller;


import com.ws.tictactoe.model.Cell;
import com.ws.tictactoe.model.Game;
import com.ws.tictactoe.model.GameSign;
import com.ws.tictactoe.model.Player;
import com.ws.tictactoe.repo.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class GameController {


    private GameRepository gameRepository;

    @SendTo("/topic/game")
    @MessageMapping("/create")
    public Game create(@RequestBody @Validated Player nextPlayer) {
        Game game = Game.builder()
                .nextPlayer(nextPlayer)
                .board(initializeGameBoard())
                .build();

        return gameRepository.getLatestGameEntry()!=null ?
                gameRepository.getLatestGameEntry().getValue():
                gameRepository.create(game);
    }

    @SendTo("/topic/game")
    @MessageMapping("/turn")
    public Game playTurn(@RequestBody @Validated Cell cell) {
        Game game = gameRepository.findById(cell.getId());
        game.playTurn(cell);
        return gameRepository.create(game);
    }


    @SendTo("/topic/game")
    @MessageMapping("/delete/{id}")
    public Game delete(@DestinationVariable String id) {
            Game game = gameRepository.findById(id);
            game =  Game.builder()
                    .gameEnded(true)
                    .build();

            gameRepository.delete(game);
            return game;
    }

    @Autowired
    public void setGameRepository(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    private GameSign[][] initializeGameBoard() {
        GameSign[][] board;
        board = new GameSign[][]{
                    {GameSign.Blank, GameSign.Blank, GameSign.Blank},
                    {GameSign.Blank, GameSign.Blank, GameSign.Blank},
                    {GameSign.Blank, GameSign.Blank, GameSign.Blank}
        };

            return board;

    }


}
