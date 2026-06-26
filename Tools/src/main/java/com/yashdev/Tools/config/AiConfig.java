package com.yashdev.Tools.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

    private ChatClient chatClient;

     AiConfig(ChatClient.Builder builder){
        this.chatClient = builder.build();
    }

    @Bean
    public ChatClient googleChatClient(){
         return  this.chatClient;
    }

}
