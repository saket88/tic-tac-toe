package com.ws.tictactoe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ws.tictactoe.GameMvcTests;
import com.ws.tictactoe.generator.GameFactory;
import com.ws.tictactoe.model.*;
import com.ws.tictactoe.repo.GameRepository;
import org.jglue.fluentjson.JsonBuilderFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@ActiveProfiles(profiles = "test")
public class GameControllerTest extends GameMvcTests{


    @Mock
    private GameFactory gameFactory;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    ObjectMapper objectMapper;

    private Game game;

    @Before
    public void setUp(){
        GameState initialState = GameState.builder()
                .nextPlayer(new Player(GameSign.X.name(),new Cell(1,1)))
                .build();
        game= new Game(initialState);
        game = gameRepository.save(game);

    }

    @Test
    public void createGame() throws Exception {

        String gameParams =
                JsonBuilderFactory.buildObject()
                        .add("firstPlayer", GameSign.O.toString())
                        .end()
                        .toString();

        mockMvc.perform(post("/games")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gameParams))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nextPlayer.gameSign").value(GameSign.O.toString()));
    }

    @Test
    public void playTurn() throws Exception {
        String turnJson = JsonBuilderFactory.buildObject()
                .addObject("move")
                .add("row", 1)
                .add("column", 1)
                .end()
                .toString();

        mockMvc.perform(post("/games/{id}/turn",game.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(turnJson))
                .andDo(print())
                .andExpect(status().isOk());

    }


}
