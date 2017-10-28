package com.ws.tictactoe.game;

import com.ws.tictactoe.GameUnitTest;
import com.ws.tictactoe.model.Game;
import com.ws.tictactoe.model.GameState;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GameTest extends GameUnitTest{

    @Mock private GameState state;

    @Test
    public void createGame() {
        Game game = new Game(state);
        assertEquals(state, game.getState());
        assertNotNull(game.getId());
    }
}
