package com.ws.tictactoe.game;

import com.ws.tictactoe.GameUnitTest;
import com.ws.tictactoe.model.Cell;
import com.ws.tictactoe.model.GameSign;
import com.ws.tictactoe.model.GameState;
import com.ws.tictactoe.model.Player;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class GameStateTest extends GameUnitTest{

    Player player;

    GameState underTest;

    GameSign[][] boardTillNow = {
            {GameSign.X, GameSign.O, GameSign.Blank},
            {GameSign.Blank, GameSign.X, GameSign.O},
            {GameSign.Blank, GameSign.Blank, GameSign.Blank}
    };



@Before
public void setUp(){
    MockitoAnnotations.initMocks(this);
}
    @Test
    public void updateAState(){
        Cell cell = new Cell(1,1);
        underTest = GameState.builder()
                .board(boardTillNow)
                .nextPlayer(new Player(GameSign.X.name()))
                .build();

        underTest.update(cell);

        assertThat(underTest.getBoard()[1][1],is(GameSign.X));


    }

    @Test
    public void determineWinnerWhenAnExpectedMoveisAtBottomRightDiagonal(){
        Cell cell = new Cell(2,2);

        underTest = GameState.builder()
                .board(boardTillNow)
                .nextPlayer(new Player(GameSign.X.name()))
                .build();

        underTest.update(cell);

        assertThat(underTest.getWinner(),is(GameSign.X));
    }

    @Test
    public void determineWinnerWhenAnExpectedMoveisAtBottomLeftDiagonal(){
        Cell cell = new Cell(2,0);

        GameSign[][] boardTillNow = {
                {GameSign.Blank, GameSign.O, GameSign.X},
                {GameSign.Blank, GameSign.X, GameSign.O},
                {GameSign.Blank, GameSign.Blank, GameSign.Blank}
        };
        underTest = GameState.builder()
                .board(boardTillNow)
                .nextPlayer(new Player(GameSign.X.name()))
                .build();

        underTest.update(cell);

        assertThat(underTest.getWinner(),is(GameSign.X));


    }

    @Test
    public void determineWinnerWhenAnExpectedMoveIsAtCentreDiagonal(){
        GameSign[][] boardTillNow = {
                {GameSign.O, GameSign.O, GameSign.X},
                {GameSign.Blank, GameSign.Blank, GameSign.O},
                {GameSign.X, GameSign.Blank, GameSign.Blank}
        };

        underTest = GameState.builder()
                .board(boardTillNow)
                .nextPlayer(new Player(GameSign.X.name()))
                .build();


        Cell cell = new Cell(1,1);

        underTest.update(cell);

        assertThat(underTest.getWinner(),is(GameSign.X));

    }

    @Test
    public void determineWinnerWhenAnExpectedMoveIsAtCenterLeftHorizontal(){
        GameSign[][] boardTillNow = {
                {GameSign.Blank, GameSign.O, GameSign.O},
                {GameSign.Blank, GameSign.X, GameSign.X},
                {GameSign.Blank, GameSign.Blank, GameSign.Blank}
        };

        underTest = GameState.builder()
                .board(boardTillNow)
                .nextPlayer(new Player(GameSign.X.name()))
                .build();

        Cell cell = new Cell(1,0);

        underTest.update(cell);

        assertThat(underTest.getWinner(),is(GameSign.X));

    }

    @Test
    public void determineWinnerWhenAnExpectedMoveIsAtCenterRightVertical(){
        GameSign[][] boardTillNow = {
                {GameSign.O, GameSign.O, GameSign.X},
                {GameSign.Blank, GameSign.Blank, GameSign.Blank},
                {GameSign.Blank, GameSign.Blank, GameSign.X}
        };

        underTest = GameState.builder()
                .board(boardTillNow)
                .nextPlayer(new Player(GameSign.X.name()))
                .build();

        Cell cell = new Cell(1,2);

        underTest.update(cell);

        assertThat(underTest.getWinner(),is(GameSign.X));

    }


    @Test
    public void determineWinnerWhenAnExpectedMoveIsAtCenterRightHorizontal(){
        GameSign[][] boardTillNow = {
                {GameSign.X, GameSign.Blank, GameSign.X},
                {GameSign.Blank, GameSign.O, GameSign.Blank},
                {GameSign.Blank, GameSign.Blank, GameSign.O}
        };

        underTest = GameState.builder()
                .board(boardTillNow)
                .nextPlayer(new Player(GameSign.X.name()))
                .build();

        Cell cell = new Cell(0,1);

        underTest.update(cell);

        assertThat(underTest.getWinner(),is(GameSign.X));

    }

    @Test
    public void determineWinnerWhenAnExpectedMoveIsAtCenterHorizontal(){
        GameSign[][] boardTillNow = {
                {GameSign.Blank, GameSign.O, GameSign.Blank},
                {GameSign.X, GameSign.Blank, GameSign.X},
                {GameSign.Blank, GameSign.Blank, GameSign.O}
        };

        underTest = GameState.builder()
                .board(boardTillNow)
                .nextPlayer(new Player(GameSign.X.name()))
                .build();

        Cell cell = new Cell(1,1);

        underTest.update(cell);

        assertThat(underTest.getWinner(),is(GameSign.X));

    }

    @Test
    public void determineWinnerWhenAnExpectedMoveIsAtCenterVertical(){
        GameSign[][] boardTillNow = {
                {GameSign.Blank, GameSign.X, GameSign.Blank},
                {GameSign.Blank, GameSign.Blank, GameSign.O},
                {GameSign.Blank, GameSign.X, GameSign.O}
        };

        underTest = GameState.builder()
                .board(boardTillNow)
                .nextPlayer(new Player(GameSign.X.name()))
                .build();

        Cell cell = new Cell(1,1);

        underTest.update(cell);

        assertThat(underTest.getWinner(),is(GameSign.X));

    }

    @Test
    public void determineATie(){
        GameSign[][] boardTillNow = {
                {GameSign.O, GameSign.X, GameSign.O},
                {GameSign.O, GameSign.X, GameSign.X},
                {GameSign.Blank, GameSign.O, GameSign.X}
        };

        underTest =GameState.builder()
                .board(boardTillNow)
                .nextPlayer(new Player(GameSign.X.name()))
                .build();

        Cell cell = new Cell(2,0);

        underTest.update(cell);

        assertThat(underTest.getWinner(),is(nullValue()));
        assertThat(underTest.isTie(),is(true));

    }
}
