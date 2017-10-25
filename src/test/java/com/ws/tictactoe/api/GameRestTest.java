package com.ws.tictactoe.api;

import com.ws.tictactoe.GameMvcTests;
import com.ws.tictactoe.model.GameSign;
import org.jglue.fluentjson.JsonBuilderFactory;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GameRestTest extends GameMvcTests {

    @Test
    public void createGame() throws Exception {
        String gameParams = JsonBuilderFactory.buildObject()
                .add("firstPlayer", String.valueOf(GameSign.O))
                .end()
                .toString();


        ResultActions  resultActions = mockMvc.perform(post("/games")
                .accept(MediaType.APPLICATION_JSON)
                .content(gameParams))
                .andDo(print())
                .andExpect(status().isCreated());

    }
}
