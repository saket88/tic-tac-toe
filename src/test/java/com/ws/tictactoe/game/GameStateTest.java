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
    @InjectMocks GameState underTest;


    @Test
    public void updateAState(){
        Cell cell = new Cell(1,1);
        given(player.getGameSign()).willReturn(GameSign.X);

        underTest.update(cell);

        assertThat(underTest.getBoard()[1][1],is(GameSign.X));


    }
}
