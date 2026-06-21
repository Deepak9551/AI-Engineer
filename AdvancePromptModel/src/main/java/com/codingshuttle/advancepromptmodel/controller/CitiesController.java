package com.codingshuttle.advancepromptmodel.controller;

import com.codingshuttle.advancepromptmodel.model.CountryCities;
import org.springframework.ai.chat.client.ChatClient;

import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CitiesController {

    private final ChatClient client;

    public CitiesController(ChatClient.Builder client) {
        this.client = client.defaultAdvisors(new SimpleLoggerAdvisor()).build();
    }
@GetMapping("/city/chat-bean")
    public ResponseEntity<CountryCities> getCountryCitiesEntity(@RequestParam String message){
        CountryCities countryCities = client.prompt()
                .user(message)
                .call()
                .entity(CountryCities.class);
        return ResponseEntity.ok(countryCities);

    }
    @GetMapping("/city/chat-list")
    public ResponseEntity<List<String>> getCountryCitiesList(@RequestParam String message){
        var countryCities  = client.prompt()
                .user(message)
                .call()
                .entity(new ListOutputConverter());
        return ResponseEntity.ok(countryCities);

    } @GetMapping("/city/chat-custom-list")
    public ResponseEntity<List<CountryCities>> getCountryCitiesCustomList(@RequestParam String message){
        var countryCities  = client.prompt()
                .user(message)
                .call()
                .entity(new ParameterizedTypeReference<List<CountryCities>>() {
                });
        return ResponseEntity.ok(countryCities);

    }
    @GetMapping("/city/chat-map")
    public ResponseEntity<Map<String,Object>> getCountryCitiesMap(@RequestParam String message){
        var countryCities  = client.prompt()
                .user(message)
                .call()
                .entity(new MapOutputConverter());
        return ResponseEntity.ok(countryCities);

    }
}
