package com.codingshuttle.advancepromptmodel.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class StreamController {

private final ChatClient client;

    public StreamController(ChatClient client) {
        this.client = client;
    }
    @GetMapping("/stream")
    public Flux<String> chatStream(@RequestParam String message){
        return client.prompt().user(message).stream().content();
    }
}
