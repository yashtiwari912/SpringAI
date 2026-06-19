package com.yashdev.springai.first.service;

import com.yashdev.springai.first.config.AiConfig;
import com.yashdev.springai.first.entity.Blog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
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
        String Response = aiConfig.ollamaChatClient()
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


        return this.aiConfig.ollamaChatClient()
                .prompt()
                .advisors(a -> a.param(
                        ChatMemory.CONVERSATION_ID,
                        "my-test-conversation"))
                .system(system ->
                        system.text(this.systemMessage))
                .user(user ->
                        user.text(this.userMessage).param("Input", query))
                .call()
                .content()
                ;
    }

    @Override
    public Flux<String> streamChat(String query) {
        return this.aiConfig.ollamaChatClient()
                .prompt()
                .system(system -> system.text(this.systemMessage))
                .user(user -> user.text(this.userMessage).param("Input", query))
                .stream()
                .content()
                .doOnNext(chunk -> log.info("Chunk received at: {}", Instant.now()));

    }
}