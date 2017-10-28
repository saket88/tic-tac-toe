package com.ws.tictactoe.game;

import com.ws.tictactoe.GameUnitTest;
import com.ws.tictactoe.model.*;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class GameTest extends GameUnitTest{

    @Mock  GameState state;


    private Player  playerX = new Player(GameSign.X.name());
    private Player  playerO = new Player(GameSign.O.name());

    @Test
    public void createGame() {
        Game game = new Game(state);
        assertEquals(state, game.getState());
        assertNotNull(game.getId());
    }

    @Test
    public void aPlayerTurn() {

        GameSign[][] expectedBoard = {
                {GameSign.X, null, null},
                {null, null, null},
                {null, null, null}
        };
        GameState gameState = GameState.builder().
                nextPlayer(playerX).build();

        Game underTest = new Game(gameState);

        underTest.playTurn(new TurnParams(new Cell(0, 0)));
        assertThat(underTest.getState().getBoard(),is(expectedBoard));
    }

}
