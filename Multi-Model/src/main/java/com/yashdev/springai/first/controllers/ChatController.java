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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class ChatController {

    //private ChatClient ollamaChatClient;

    //private ChatClient googleChatClient;

//    public ChatController(@Qualifier("ollamaChatClient") ChatClient ollamaChatClient, @Qualifier("googleChatClient") ChatClient googleChatClient) {
//        this.ollamaChatClient = ollamaChatClient;
//        this.googleChatClient = googleChatClient;
//    }

    @Autowired
    private ChatServiceImpl chatService;

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam(value = "q",required = true) String query){

        return ResponseEntity.ok(chatService.chatTemplate(query));
    }
}
