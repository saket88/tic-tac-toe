package com.ws.tictactoe.game;

import com.ws.tictactoe.GameUnitTest;
import com.ws.tictactoe.model.Cell;
import com.ws.tictactoe.model.GameSign;
import com.ws.tictactoe.model.GameState;
import com.ws.tictactoe.model.Player;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class GameStateTest extends GameUnitTest{

    GameState underTest;

    GameSign[][] boardTillNow = {
            {GameSign.X, GameSign.O, GameSign.Blank},
            {GameSign.Blank, GameSign.X, GameSign.O},
            {GameSign.Blank, GameSign.Blank, GameSign.Blank}
    };



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
        Cell cell = new Cell(1,1);

        GameSign[][] boardTillNow = {
                {GameSign.O, GameSign.O, GameSign.X},
                {GameSign.Blank, GameSign.Blank, GameSign.O},
                {GameSign.X, GameSign.Blank, GameSign.Blank}
        };

        underTest = GameState.builder()
                .board(boardTillNow)
                .nextPlayer(new Player(GameSign.X.name()))
                .build();




        underTest.update(cell);

        assertThat(underTest.getWinner(),is(GameSign.X));

    }

    @Test
    public void determineWinnerWhenAnExpectedMoveIsAtCenterLeftHorizontal(){
        Cell cell = new Cell(1,0);

        GameSign[][] boardTillNow = {
                {GameSign.Blank, GameSign.O, GameSign.O},
                {GameSign.Blank, GameSign.X, GameSign.X},
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
    public void determineWinnerWhenAnExpectedMoveIsAtCenterRightVertical(){
        Cell cell = new Cell(1,2);

        GameSign[][] boardTillNow = {
                {GameSign.O, GameSign.O, GameSign.X},
                {GameSign.Blank, GameSign.Blank, GameSign.Blank},
                {GameSign.Blank, GameSign.Blank, GameSign.X}
        };

        underTest = GameState.builder()
                .board(boardTillNow)
                .nextPlayer(new Player(GameSign.X.name()))
                .build();


        underTest.update(cell);

        assertThat(underTest.getWinner(),is(GameSign.X));

    }


    @Test
    public void determineWinnerWhenAnExpectedMoveIsAtCenterRightHorizontal(){
        Cell cell = new Cell(0,1);

        GameSign[][] boardTillNow = {
                {GameSign.X, GameSign.Blank, GameSign.X},
                {GameSign.Blank, GameSign.O, GameSign.Blank},
                {GameSign.Blank, GameSign.Blank, GameSign.O}
        };

        underTest = GameState.builder()
                .board(boardTillNow)
                .nextPlayer(new Player(GameSign.X.name()))
                .build();


        underTest.update(cell);

        assertThat(underTest.getWinner(),is(GameSign.X));

    }

    @Test
    public void determineWinnerWhenAnExpectedMoveIsAtCenterHorizontal(){
        Cell cell = new Cell(1,1);

        GameSign[][] boardTillNow = {
                {GameSign.Blank, GameSign.O, GameSign.Blank},
                {GameSign.X, GameSign.Blank, GameSign.X},
                {GameSign.Blank, GameSign.Blank, GameSign.O}
        };

        underTest = GameState.builder()
                .board(boardTillNow)
                .nextPlayer(new Player(GameSign.X.name()))
                .build();



        underTest.update(cell);

        assertThat(underTest.getWinner(),is(GameSign.X));

    }

    @Test
    public void determineWinnerWhenAnExpectedMoveIsAtCenterVertical(){
        Cell cell = new Cell(1,1);

        GameSign[][] boardTillNow = {
                {GameSign.Blank, GameSign.X, GameSign.Blank},
                {GameSign.Blank, GameSign.Blank, GameSign.O},
                {GameSign.Blank, GameSign.X, GameSign.O}
        };

        underTest = GameState.builder()
                .board(boardTillNow)
                .nextPlayer(new Player(GameSign.X.name()))
                .build();


        underTest.update(cell);

        assertThat(underTest.getWinner(),is(GameSign.X));

    }

    @Test
    public void determineATie(){
        Cell cell = new Cell(2,0);

        GameSign[][] boardTillNow = {
                {GameSign.O, GameSign.X, GameSign.O},
                {GameSign.O, GameSign.X, GameSign.X},
                {GameSign.Blank, GameSign.O, GameSign.X}
        };

        underTest =GameState.builder()
                .board(boardTillNow)
                .nextPlayer(new Player(GameSign.X.name()))
                .build();



        underTest.update(cell);

        assertThat(underTest.getWinner(),is(nullValue()));
        assertThat(underTest.isTie(),is(true));

    }


    @Test
    public void determineTheStartAndEndIndexWhenAWin(){
        Cell cell = new Cell(1,2);

        GameSign[][] boardTillNow = {
                {GameSign.O, GameSign.O, GameSign.X},
                {GameSign.O, GameSign.X, GameSign.Blank},
                {GameSign.X, GameSign.O, GameSign.X}
        };

        underTest =GameState.builder()
                .board(boardTillNow)
                .nextPlayer(new Player(GameSign.X.name()))
                .build();



        underTest.update(cell);


        assertThat(underTest.getWinningSequence().getStart().getRow(),is(2));
        assertThat(underTest.getWinningSequence().getStart().getColumn(),is(2));
        assertThat(underTest.getWinningSequence().getEnd().getRow(),is(0));
        assertThat(underTest.getWinningSequence().getEnd().getColumn(),is(2));

    }
}
