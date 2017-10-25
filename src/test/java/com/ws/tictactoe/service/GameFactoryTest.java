package com.ws.tictactoe.service;

import com.ws.tictactoe.GameUnitTest;
import com.ws.tictactoe.generator.GameFactory;
import com.ws.tictactoe.model.*;
import org.junit.Test;
import org.mockito.InjectMocks;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class GameFactoryTest extends GameUnitTest {

    @InjectMocks
    GameFactory underTest;

    @Test
    public void createGame(){

        GameParams gameParams= mock(GameParams.class);
        given(gameParams.getFirstPlayer()).willReturn(new Player(GameSign.O));

        Game game = underTest.createGame(gameParams);

        assertNotNull(game.getState());
        assertThat(game.getState().getNextPlayer(),is(gameParams.getFirstPlayer()));

    }

}
