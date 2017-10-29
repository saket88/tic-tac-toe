package com.ws.tictactoe.game;

import com.ws.tictactoe.GameUnitTest;
import com.ws.tictactoe.model.Cell;
import com.ws.tictactoe.model.GameSign;
import com.ws.tictactoe.model.GameState;
import com.ws.tictactoe.model.Player;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;

public class GameStateTest extends GameUnitTest{

    @Mock Player player;

     @InjectMocks  GameState underTest;

    GameSign[][] boardTillNow = {
            {GameSign.X, GameSign.O, GameSign.Blank},
            {GameSign.Blank, GameSign.X, GameSign.O},
            {GameSign.Blank, GameSign.Blank, GameSign.Blank}
    };




    @Test
    public void updateAState(){
        Cell cell = new Cell(1,1);
        given(player.getGameSign()).willReturn(GameSign.X);

        underTest.update(cell);

        assertThat(underTest.getBoard()[1][1],is(GameSign.X));


    }

    @Test
    public void determineWinnerWhenAnExpectedMoveisAtBottomRightDiagonal(){
        Cell cell = new Cell(2,2);

        underTest =GameState.builder().board(boardTillNow)
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

        underTest =GameState.builder().board(boardTillNow)
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

        underTest =GameState.builder().board(boardTillNow)
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

        underTest =GameState.builder().board(boardTillNow)
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

        underTest =GameState.builder().board(boardTillNow)
                .nextPlayer(new Player(GameSign.X.name()))
                .build();

        Cell cell = new Cell(1,2);

        underTest.update(cell);

        assertThat(underTest.getWinner(),is(GameSign.X));

    }
}
