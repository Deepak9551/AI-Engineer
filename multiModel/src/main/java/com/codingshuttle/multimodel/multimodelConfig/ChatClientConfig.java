package com.codingshuttle.multimodel.multimodelConfig;

import org.springframework.ai.bedrock.converse.BedrockProxyChatModel;
import org.springframework.ai.chat.client.ChatClient;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {



    @Bean(name = "openRouterChatClient")
    public ChatClient openRouterChatClient(OpenAiChatModel openAiChatModel) {
        return ChatClient.builder(openAiChatModel).build();
    }

@Bean(name = "awsBedrockChatClient")
    public ChatClient awsBedrockChatClient(BedrockProxyChatModel chatModel) {
        return ChatClient.create(chatModel);
    }


}
