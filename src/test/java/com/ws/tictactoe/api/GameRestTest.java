package com.ws.tictactoe.api;



import com.ws.tictactoe.model.Game;
import com.ws.tictactoe.repo.GameRepository;
import org.jglue.fluentjson.JsonBuilderFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GameRestTest extends GameRestSteps {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private GameRepository gameRepository;

    @Test
    public void createGame() {
        ResponseEntity<Game> responseEntity = createRestGame(restTemplate,"/games");
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.CREATED));
    }



    @Test
    public void onAPlayersTurn(){
        ResponseEntity<Game> responseEntity = createRestGame(restTemplate,"/games");
        String gameId = responseEntity.getBody().getId();
        String turnJson = JsonBuilderFactory.buildObject()
                .add("row", 1)
                .add("column", 1)
                .toString();

        assertThat(postFor(restTemplate,turnJson,"/games/"+gameId+"/turn").getStatusCode(),is(HttpStatus.OK));



    }
}
