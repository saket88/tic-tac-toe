package com.ws.tictactoe.repository;

import com.ws.tictactoe.GameUnitTest;
import com.ws.tictactoe.model.Cell;
import com.ws.tictactoe.model.Game;
import com.ws.tictactoe.model.GameState;
import com.ws.tictactoe.model.Player;
import com.ws.tictactoe.repo.GameRepository;
import org.junit.Test;
import org.mockito.InjectMocks;

import static com.ws.tictactoe.model.GameSign.O;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GameRepositoryTest extends GameUnitTest{

    @InjectMocks
    GameRepository underTest;

    @Test
    public void saveTheGame(){

        Game game = underTest.save(new Game(GameState.builder()
        .nextPlayer(new Player("O",new Cell(1,1)))
        .build()));

        assertThat(game,is(underTest.findById(game.getId())));
        assertThat(game.getState().getNextPlayer().getGameSign(),is(O));

    }
}
