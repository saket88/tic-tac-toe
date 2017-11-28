package com.ws.tictactoe.controller;


import com.ws.tictactoe.model.Game;
import com.ws.tictactoe.model.GameSign;
import com.ws.tictactoe.model.Player;
import com.ws.tictactoe.repo.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@SendTo("/topic/game")
public class GameController {


    private GameRepository gameRepository;

    @MessageMapping("/create")
    public Game create(@RequestBody @Validated Player nextPlayer) {
        Game game = Game.builder()
                .nextPlayer(nextPlayer)
                .board(initializeGameBoard())
                .build();

        return gameRepository.getLatestGameEntry()!=null ?gameRepository.getLatestGameEntry().getValue():gameRepository.create(game);
    }

//    @RequestMapping(value = "/{id}/turn",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
//    public Game playTurn(@RequestBody @Valid   hyhhg```ated Cell cell,
//                            @PathVariable String id) {
//        Game game = gameRepository.findById(id);
//        game.playTurn(cell);
//        return gameRepository.create(game);
//    }
//
//
//    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void delete(@PathVariable String id) {
//            Game game = gameRepository.findById(id);
//            gameRepository.delete(game);
//    }
//
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
