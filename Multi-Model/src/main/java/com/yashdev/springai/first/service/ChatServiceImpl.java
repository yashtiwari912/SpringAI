package com.yashdev.springai.first.service;

import com.yashdev.springai.first.config.AiConfig;
import com.yashdev.springai.first.entity.Blog;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private AiConfig aiConfig;

    @Value("classpath:/prompts/user-message.st")
    private Resource userMessage;
    @Value("classpath:/prompts/system-message.st")
    private Resource systemMessage;

    public String chat(String query) {

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

        Prompt prompt = new Prompt(query);

//        List<Blog> Response = aiConfig.googleChatClient()
//                .prompt(prompt)
//                .call()
//                .entity(new ParameterizedTypeReference<List<Blog>>() {
//                });


        //Test for dynamic query
        String testQuery = "Act as an expert Coding Assistant and write programms in java for the following query: {query}";
        String Response = aiConfig.googleChatClient()
                .prompt()
                .user(u -> u.text(testQuery).param("query", query))
                .call()
                .content();

        return Response;
    }

    public String chatTemplate(String query) {


        //first step
//        PromptTemplate strTemplate = PromptTemplate.builder().template("What is {techName}? tell ma also about {techExample}").build();
//
//        //render the template
//
//        String renderedMessage = strTemplate.render(Map.of(
//                "techName", "Spring",
//                "techExample", "spring exception"
//        ));
//
//
//        Prompt prompt = new Prompt(renderedMessage);

//
//        var systemPromptTemplate=SystemPromptTemplate.builder()
//                .template("You are a helpful coding assistant. You are an expert in coding.")
//                .build();
//        var systemMessage=systemPromptTemplate.createMessage();
//
//        var userPromptTemplate=PromptTemplate.builder().template("What is {techName}? tell ma also about {techExample}").build();
//        var userMessage=userPromptTemplate.createMessage(Map.of(
//                "techName", "Spring",
//                "techExample", "spring exception"
//        ));


//        Prompt prompt = new Prompt(systemMessage,userMessage);


        return this.aiConfig.googleChatClient()
                .prompt()
                .system(system ->
                        system.text(this.systemMessage))
                .user(user ->
                        user.text(this.userMessage).param("concept", query))
                .call()
                .content()
                ;
    }
}