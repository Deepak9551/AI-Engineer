package com.codingshuttle.chatmemory.controller;

import com.codingshuttle.chatmemory.dto.modelResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ResponseEntity;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatMemoryController {
private final ChatClient chatClient;
private final ChatClient memoryChatClient;
    public ChatMemoryController(@Qualifier("ChatClient") ChatClient chatClient,@Qualifier("MemoryChatClient") ChatClient memoryChatClient) {
        this.chatClient = chatClient;
        this.memoryChatClient = memoryChatClient;
    }

    @GetMapping("/no-memory")
    public ResponseEntity<ChatResponse , modelResponse> withOutChatMemory(String message){
    return     chatClient.prompt()
                .user(message).call().responseEntity(modelResponse.class);

    }

    @GetMapping("/memory")
    public ResponseEntity<ChatResponse , modelResponse> ChatMemory(String username ,String message){
        return     memoryChatClient.prompt().
        advisors(advisor -> advisor.param(
                ChatMemory.CONVERSATION_ID,
                username))

                .user(message).call().responseEntity(modelResponse.class);

    }
}
