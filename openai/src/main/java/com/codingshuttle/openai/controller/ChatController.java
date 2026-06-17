package com.codingshuttle.openai.controller;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChatController {

    private final ChatClient client;

    ChatController(ChatModel chatModel) {
        this.client =  ChatClient.builder(chatModel).build();
    }

    // create a controller by which client can sent prompt to the AI Provider(model)
    // that prompt we send to the model and response back to the client
    @GetMapping("/chat")
    public String sendPrompt(@RequestParam String prompt){
        System.out.println("Called");
       var response = client.prompt()
                .user(prompt)
                .call()
                .content();
        System.out.println(response);
        return response;
    }
}
