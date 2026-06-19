package com.codingshuttle.advancepromptmodel.controller;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ChatController {

    private final ChatClient client;



    ChatController(ChatClient chatClient) {
        this.client = chatClient;
    }

    // create a controller by which client can sent prompt to the AI Provider(model)
    // that prompt we send to the model and response back to the client
    @GetMapping("IT/chat")
    public String sendITPrompt(@RequestParam String prompt){
        System.out.println("Called");
       var response = client.prompt()
                .user(prompt)
               .system("""
                        You are an internal IT helpdesk assistant. Your role is to assist
                        employees with IT-related issues such as resetting passwords,
                        unlocking accounts, and answering questions related to IT policies.
                        If a user requests help with anything outside of these
                        responsibilities, respond politely and inform them that you are
                        only able to assist with IT support tasks within your defined scope.
                        """)
                .call()
                .content();
        System.out.println(response);
        return response;
    }
    @GetMapping("/food/chat")
    public String foodPrompt(@RequestParam String prompt){
        System.out.println("Called");
        var response = client.prompt()
                //.user(prompt)
                .system("""
                        You are a food recommendation assistant.
                        Recommend foods based on the current time of day
                        (breakfast, lunch, snacks, dinner, or late night). Suggest tasty, popular, and balanced meals.
                        Explain briefly why each food is a good choice for that time. Keep responses short, friendly, and practical.
                        """)
                .call()
                .content();
        System.out.println(response);
        return response;
    }

    @GetMapping("/chat")
    public String sendPrompt(@RequestParam String prompt){
        System.out.println("Called");
        var response = client.prompt()
                .user(prompt)
//                .advisors(new SimpleLoggerAdvisor())
                .call()
                .content();
        System.out.println(response);
        return response;
    }
}
