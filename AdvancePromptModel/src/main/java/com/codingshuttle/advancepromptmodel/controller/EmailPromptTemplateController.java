package com.codingshuttle.advancepromptmodel.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping()
public class EmailPromptTemplateController {
    private final ChatClient client;

    @Value("classpath:/promptTemplate/EmailPromptTemplate.st")
    private Resource emailTemplate;

    @Value("classpath:/promptTemplate/SystemPrompt.st")
    private Resource systemTemplate;


    EmailPromptTemplateController(ChatClient chatClient) {
        this.client = chatClient;
    }

    @GetMapping("/email/chat")
    public String sendPrompt(@RequestParam String customerName,String customerMessage){
        System.out.println("Called");
        var response = client.prompt()
                .system("""
                        You are a professional customer service assistant which helps drafting email
                        responses to improve the productivity of the customer support team
                        """)
                .user(promptTemplateSpec ->
                    promptTemplateSpec.text(emailTemplate)
                            .param("customerName", customerName)
                            .param("customerMessage", customerMessage))
                .call()
                .content();
        System.out.println(response);
        return response;
    }

    @GetMapping("/leave/chat")
    public String sendCompanyLeavePrompt(@RequestParam String message){
        System.out.println("Called");
        var response = client.prompt()
                .system(systemTemplate)
                .user(message)
                .call()
                .content();
        System.out.println(response);
        return response;
    }
}
