package com.yashdev.springai.first.config;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
//import org.springframework.ai.chat.memory.repository.mongo.MongoChatMemoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatMemoryConfig {
    //Moved the ChatMemory bean from AiConfig to a separate configuration class to avoid dependency cycle between beans.
//    @Bean
//    public ChatMemory chatMemory(MongoChatMemoryRepository chatMemoryRepository){
//        return MessageWindowChatMemory.builder()
//                .chatMemoryRepository(chatMemoryRepository)
//                .maxMessages(10)
//                .build();
//    }

    //InMemoryChatMemory
    @Bean
    public ChatMemory chatMemory(){
        InMemoryChatMemoryRepository chatMemoryRepository = new InMemoryChatMemoryRepository();
        return MessageWindowChatMemory.builder()
                .chatMemoryRepository(chatMemoryRepository)
                .maxMessages(10)
                .build();
    }

}
