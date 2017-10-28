package com.ws.tictactoe.api;

import com.ws.tictactoe.model.GameDTO;
import com.ws.tictactoe.model.GameSign;
import org.jglue.fluentjson.JsonBuilderFactory;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class GameRestSteps {
    public  static ResponseEntity<GameDTO> createRestGame(TestRestTemplate restTemplate,String url) {
        String gameParams =
                JsonBuilderFactory.buildObject()
                        .add("firstPlayer", GameSign.O.toString())
                        .end()
                        .toString();
        return postFor(restTemplate, gameParams,url);
    }

    public static ResponseEntity<GameDTO> postFor(TestRestTemplate restTemplate, String gameParams , String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(gameParams, headers);
        return restTemplate.postForEntity(url, entity, GameDTO.class);
    }
}
