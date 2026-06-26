package com.yashdev.Tools.service;

import com.yashdev.Tools.config.AiConfig;
import com.yashdev.Tools.tools.GetDateAndTime;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService{

    @Autowired
    private AiConfig aiConfig;

    @Override
    public String chat(String query) {
        return aiConfig.googleChatClient()
                .prompt()
                .advisors(new SimpleLoggerAdvisor())
                .tools(new GetDateAndTime())
                .user(query)
                .call()
                .content();
    }
}
