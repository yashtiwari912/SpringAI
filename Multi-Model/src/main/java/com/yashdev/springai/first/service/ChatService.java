package com.yashdev.springai.first.service;

import com.yashdev.springai.first.entity.Blog;
import reactor.core.publisher.Flux;

import java.util.List;

public interface ChatService {
        String chat(String query);
        String chatTemplate(String query);

        Flux<String> streamChat(String query);
}
