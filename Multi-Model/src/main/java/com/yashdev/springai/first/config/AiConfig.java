package com.yashdev.springai.first.config;

import com.yashdev.springai.first.advisors.TokenPrintAdvisor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
//import org.springframework.ai.google.genai.GoogleGenAiChatModel;
//import org.springframework.ai.ollama.OllamaChatModel;
//import org.springframework.ai.google.genai.GoogleGenAiChatModel;
//import org.springframework.ai.google.genai.GoogleGenAiChatOptions;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Slf4j
@Configuration
public class AiConfig {

//    @Bean(name = "ollamaChatClient")
//    public ChatClient ollamaChatClient(OllamaChatModel ollamaChatModel) {
//        return ChatClient.builder(ollamaChatModel).build();
//    }

//    @Bean(name = "googleChatClient")
//    public ChatClient googleChatClient(GoogleGenAiChatModel googleGenAiChatModel) {
//        return ChatClient.builder(googleGenAiChatModel).build();
//    }

    private ChatClient chatClient;

    public AiConfig(ChatClient.Builder builder, ChatMemory chatMemory) {

        //Implemeting ChatMemory
        log.info("ChatMemory Implementation Class: "+ chatMemory.getClass().getName());

        MessageChatMemoryAdvisor messageChatMemoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();

        this.chatClient = builder.
                defaultAdvisors(messageChatMemoryAdvisor,new TokenPrintAdvisor(),new SimpleLoggerAdvisor(),
                        new SafeGuardAdvisor(List.of("games"))
                ).
                defaultOptions(
                OllamaChatOptions.builder()
                        .model("phi4-mini:3.8b")
                        .temperature(0.7)

                )
                .build();
    }


    @Bean
    public ChatClient ollamaChatClient() {
        return this.chatClient;
    }
}
