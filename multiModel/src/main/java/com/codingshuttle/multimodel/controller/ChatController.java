package com.codingshuttle.multimodel.controller;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ChatController {
    private final  ChatClient openRouterClient;
    private final ChatClient awsBedRockClient;

    @Autowired
    public ChatController(@Qualifier("openRouterChatClient") ChatClient openRouterClient,
                          @Qualifier("awsBedrockChatClient") ChatClient awsBedRockClient) {
        this.openRouterClient = openRouterClient;
        this.awsBedRockClient = awsBedRockClient;
    }

    // create a controller by which client can sent prompt to the AI Provider(model) API
    // that prompt we send to the model and response back to the client
    @GetMapping("/openrouter/chat")
    public String sendPromptToOpenRouter(@RequestParam String prompt){
        System.out.println("Called");
       var response = openRouterClient.prompt()
                .user(prompt)
                .call()
                .content();
        System.out.println(response);
        return response;
    }
    @GetMapping("/aws/chat")
    public String sendPromptToAWSBedrock(@RequestParam String prompt){
        System.out.println("Called");
        var response = awsBedRockClient.prompt()
                .user(prompt)
                .call()
                .content();
        System.out.println(response);
        return response;
    }
}
