package com.yashdev.springai.first.controllers;

import com.yashdev.springai.first.entity.Blog;
import com.yashdev.springai.first.service.ChatService;
import com.yashdev.springai.first.service.ChatServiceImpl;
import org.springframework.ai.chat.client.ChatClient;
//import org.springframework.ai.google.genai.GoogleGenAiChatModel;
//import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping
public class ChatController {

    //private ChatClient googleChatClient;

    //private ChatClient googleChatClient;

//    public ChatController(@Qualifier("googleChatClient") ChatClient googleChatClient, @Qualifier("googleChatClient") ChatClient googleChatClient) {
//        this.googleChatClient = googleChatClient;
//        this.googleChatClient = googleChatClient;
//    }

    @Autowired
    private ChatServiceImpl chatService;

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam(value = "q",required = true) String query,
     @RequestHeader(value="userId",required = true)String userId){

            return ResponseEntity.ok(chatService.chatTemplate(query,userId));
    }
    @GetMapping("/stream-chat")
    public Flux<String>streamChat(
            @RequestParam(value = "q",required = true) String query

    ){
        return chatService.streamChat(query);
    }
}
