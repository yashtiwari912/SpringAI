package com.yashdev.springai.first.config;

import com.yashdev.springai.first.advisors.TokenPrintAdvisor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.google.genai.GoogleGenAiChatOptions;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@Configuration
public class AiConfig {

    private ChatClient chatClient;

    public AiConfig(ChatClient.Builder builder) {
        this.chatClient = builder.
                defaultAdvisors(new TokenPrintAdvisor(),new SimpleLoggerAdvisor(),
                        new SafeGuardAdvisor(List.of("games"))
                ).
                defaultOptions(
                        GoogleGenAiChatOptions.builder()
                        .model("gemini-2.5-flash")
                        .temperature(0.7)

                )
                .build();
    }


    @Bean
    public ChatClient googleChatClient() {
        return this.chatClient;
    }
}
