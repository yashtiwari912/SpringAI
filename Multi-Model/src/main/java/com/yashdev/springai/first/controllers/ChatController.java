package com.yashdev.springai.first.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.google.genai.GoogleGenAiChatModel;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ChatController {

    private ChatClient ollamaChatClient;

    private ChatClient googleChatClient;


    public ChatController(@Qualifier("ollamaChatClient") ChatClient ollamaChatClient, @Qualifier("googleChatClient") ChatClient googleChatClient) {
        this.ollamaChatClient = ollamaChatClient;
        this.googleChatClient = googleChatClient;
    }

    @GetMapping("/chat")
    public ResponseEntity<String>chat(@RequestParam(value = "q",required = true) String query){
        var Response = googleChatClient
                .prompt(query)
                .call()
                .content();
        return ResponseEntity.ok(Response);
    }
}
