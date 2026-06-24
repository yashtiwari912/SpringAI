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


    @Autowired
    private ChatServiceImpl chatService;

   @PostMapping("/chat")
   public ResponseEntity<String> getResponse(@RequestParam("q") String userQuery){
       return ResponseEntity.ok(chatService.getResponse(userQuery));
   }
}
