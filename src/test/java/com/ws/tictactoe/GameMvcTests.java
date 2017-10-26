package com.ws.tictactoe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;


@AutoConfigureMockMvc
@SpringBootTest(classes = GameApplication.class)
@WebAppConfiguration
public abstract class GameMvcTests extends GameUnitTest{


    @Autowired
    protected MockMvc mockMvc;
}
