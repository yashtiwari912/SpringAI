package com.yashdev.springai.first.config;

import com.yashdev.springai.first.advisors.TokenPrintAdvisor;
import org.springframework.ai.chat.client.ChatClient;
//import org.springframework.ai.google.genai.GoogleGenAiChatModel;
//import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.google.genai.GoogleGenAiChatModel;
import org.springframework.ai.google.genai.GoogleGenAiChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    public AiConfig(ChatClient.Builder builder) {
        this.chatClient = builder.
                defaultAdvisors(new TokenPrintAdvisor(),new SimpleLoggerAdvisor()).
                defaultOptions(
                GoogleGenAiChatOptions.builder()
                        .model("gemini-2.5-flash")
                        .temperature(0.7)
                        .maxTokens(500)

                )
                .build();
    }

    @Bean
    public ChatClient googleChatClient() {
        return this.chatClient;
    }
}
