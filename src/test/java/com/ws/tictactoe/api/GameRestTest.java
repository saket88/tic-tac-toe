package com.ws.tictactoe.api;


import com.ws.tictactoe.model.GameDTO;
import com.ws.tictactoe.model.GameSign;
import org.jglue.fluentjson.JsonBuilderFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GameRestTest{
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void createGame() {
        String gameParams =
                JsonBuilderFactory.buildObject()
                        .add("firstPlayer", GameSign.O.toString())
                        .end()
                        .toString();


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(gameParams, headers);

        ResponseEntity<GameDTO> responseEntity =
                restTemplate.postForEntity("/games", entity, GameDTO.class);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.CREATED));

    }
}
