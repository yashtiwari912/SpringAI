package com.yashdev.springai.first.service;

import com.yashdev.springai.first.config.AiConfig;
import com.yashdev.springai.first.entity.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private AiConfig aiConfig;
    public List<Blog> chat(String query) {

//        String Response = aiConfig.googleChatClient()
//                .prompt(query)
//                .call()
//                .content();

//        String Response = aiConfig.googleChatClient()
//                .prompt()
//                .user(query)
//                .system("You are a wrestling analyst")
//                .call()
//                .content();

//        Blog Response = aiConfig.googleChatClient()
//                .prompt(query)
//                .call()
//                .entity(Blog.class);

        List<Blog> Response = aiConfig.googleChatClient()
                .prompt(query)
                .call()
                .entity(new ParameterizedTypeReference<List<Blog>>() {
                });

        return Response;
    }
}
