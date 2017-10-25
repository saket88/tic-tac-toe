package com.ws.tictactoe.controller;

import com.ws.tictactoe.GameMvcTests;
import com.ws.tictactoe.generator.GameFactory;
import com.ws.tictactoe.model.Game;
import com.ws.tictactoe.model.GameSign;
import com.ws.tictactoe.repo.GameRepository;
import org.jglue.fluentjson.JsonBuilderFactory;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GameControllerTest extends GameMvcTests{


    @Mock
    private GameFactory gameFactory;

    @Mock
    private GameRepository gameRepository;

    @Test
    public void createGame() throws Exception {

        Game game = mock(Game.class);
        String gameParams =
                JsonBuilderFactory.buildObject()
                        .add("firstPlayer", GameSign.O.toString())
                        .end()
                        .toString();

        given(gameFactory.createGame(gameParams)).willReturn(game);
        given(gameRepository.save(game)).willReturn(game);

        ResultActions resultActions = mockMvc.perform(post("/games")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gameParams))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nextPlayer").value(GameSign.O.toString()))
                ;



    }
}
