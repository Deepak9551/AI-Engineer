package com.codingshuttle.advancepromptmodel.config;

import com.codingshuttle.advancepromptmodel.advisors.TokenLogsAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ChatClientConfig {


    @Bean
    public ChatClient chatClient(ChatClient.Builder chatBuilder){
        return chatBuilder.defaultSystem("""
                You are a helpful assistant.
                Answer the user's questions clearly and accurately.
                Provide practical explanations, step-by-step guidance when needed, and examples to improve understanding.
                Keep responses concise, friendly, and easy to follow.
                """)
                .defaultAdvisors(List.of(new SimpleLoggerAdvisor(),new TokenLogsAdvisor()))
                .defaultUser("""
                        How can you help me ?
                        """)
                .build();
    }
}
