package com.ws.tictactoe.api;

import com.ws.tictactoe.model.Game;
import com.ws.tictactoe.model.GameSign;
import org.jglue.fluentjson.JsonBuilderFactory;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class GameRestSteps {
    public  static ResponseEntity<Game> createRestGame(TestRestTemplate restTemplate,String url) {
        String gameParams =
                JsonBuilderFactory.buildObject()
                        .add("gameSign", GameSign.O.toString())
                        .end()
                        .toString();
        return postFor(restTemplate, gameParams,url);
    }

    public static ResponseEntity<Game> postFor(TestRestTemplate restTemplate, String gameParams , String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(gameParams, headers);
        return restTemplate.postForEntity(url, entity, Game.class);
    }
}
