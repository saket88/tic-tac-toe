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
                {GameSign.X, GameSign.Blank, GameSign.Blank},
                {GameSign.Blank, GameSign.Blank, GameSign.Blank},
                {GameSign.Blank, GameSign.Blank, GameSign.Blank}
        };
        GameState gameState = GameState.builder().
                nextPlayer(playerX).build();

        Game underTest = new Game(gameState);

        underTest.playTurn(new TurnParams(new Cell(0, 0)));
        assertThat(underTest.getState().getBoard(),is(expectedBoard));
        assertThat(underTest.getState().getNextPlayer().getGameSign(),is(playerO.getGameSign()));
    }

    @Test
    public void aPlayerWins() {

        GameSign[][] boardTillNow = {
                {GameSign.X, GameSign.O, GameSign.Blank},
                {GameSign.Blank, GameSign.X, GameSign.O},
                {GameSign.Blank, GameSign.Blank, GameSign.Blank}
        };
        GameState gameState = GameState.builder()
                .board(boardTillNow)
                .nextPlayer(playerX).build();

        Game underTest = new Game(gameState);

        underTest.playTurn(new TurnParams(new Cell(2, 2)));

        assertThat(underTest.getState().getWinner(),is(GameSign.X));
    }
}
