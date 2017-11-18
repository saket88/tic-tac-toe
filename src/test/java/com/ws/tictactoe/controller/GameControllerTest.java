package com.ws.tictactoe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ws.tictactoe.GameMvcTests;
import com.ws.tictactoe.model.Game;
import com.ws.tictactoe.model.GameSign;
import com.ws.tictactoe.model.Player;
import com.ws.tictactoe.repo.GameRepository;
import org.jglue.fluentjson.JsonBuilderFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@ActiveProfiles(profiles = "test")
public class GameControllerTest extends GameMvcTests{



    @Autowired
    private GameRepository gameRepository;

    @Autowired
    ObjectMapper objectMapper;

    private Game game;

    @Before
    public void setUp(){
        Game gameInit = Game.builder()
                .nextPlayer(new Player(GameSign.X.name()))
                .build();

        game = gameRepository.create(gameInit);

    }

    @Test
    public void createGame() throws Exception {

        String gameParams =
                JsonBuilderFactory.buildObject()
                        .add("gameSign", GameSign.O.toString())
                        .end()
                        .toString();

        mockMvc.perform(post("/games")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gameParams))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.currentPlayer.gameSign").value(GameSign.O.toString()));
    }

    @Test
    public void playTurn() throws Exception {
        String turnJson = JsonBuilderFactory.buildObject()
                .add("row", 1)
                .add("column", 1)
                .toString();

        mockMvc.perform(post("/games/{id}/turn",game.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(turnJson))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void deleteGame() throws Exception {
        String turnJson = JsonBuilderFactory.buildObject()
                .add("row", 1)
                .add("column", 1)
                .toString();

        mockMvc.perform(post("/games/{id}/turn",game.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(turnJson));

        mockMvc.perform(delete("/games/"+game.getId()))
                .andExpect(status().isNoContent());


    }


}
