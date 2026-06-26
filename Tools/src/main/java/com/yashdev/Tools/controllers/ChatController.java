package com.yashdev.Tools.controllers;

import com.yashdev.Tools.service.ChatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping("/chat")
    public ResponseEntity<String> chat(
            @RequestParam(value = "q",required = true) String query
    ){
        return ResponseEntity.ok(chatService.chat(query));
    }

}
