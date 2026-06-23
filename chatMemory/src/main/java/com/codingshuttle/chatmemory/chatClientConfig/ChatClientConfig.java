package com.codingshuttle.chatmemory.chatClientConfig;

import org.springframework.ai.chat.client.ChatClient;

import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.util.List;

@Configuration
public class ChatClientConfig {

    @Value("classpath:/promptTemplate/SystemDefaultPrompt.st")
    private Resource SYSTEM_DEFAULT_MESSAGE;

    @Value("classpath:/promptTemplate/UserDefaultPrompt.st")
    private Resource USER_DEFAULT_MESSAGE;
@Bean("ChatClient")
    public ChatClient chatClient(ChatClient.Builder chatBuilder){
        return chatBuilder.defaultSystem(SYSTEM_DEFAULT_MESSAGE) .defaultAdvisors(List.of(new SimpleLoggerAdvisor()))
                .defaultUser(USER_DEFAULT_MESSAGE).build();
    }

    @Bean("MemoryChatClient")
    public ChatClient memoryChatClient(ChatClient.Builder chatBuilder, ChatMemory chatMemory){
        Advisor simpleLoggerAdvisor = new SimpleLoggerAdvisor();
        MessageChatMemoryAdvisor memoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();
        return chatBuilder.defaultSystem(SYSTEM_DEFAULT_MESSAGE)
                .defaultAdvisors(List.of(simpleLoggerAdvisor,memoryAdvisor))
                .defaultUser(USER_DEFAULT_MESSAGE).build();
    }

    public ChatMemory chatmemory(JdbcChatMemoryRepository jdbcChatMemoryRepository){
    return MessageWindowChatMemory.builder().chatMemoryRepository(jdbcChatMemoryRepository)
            .maxMessages(50)
            .build();
    }

}
