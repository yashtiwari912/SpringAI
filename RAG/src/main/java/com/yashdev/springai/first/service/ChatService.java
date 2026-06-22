package com.yashdev.springai.first.service;

import com.yashdev.springai.first.entity.Blog;
import reactor.core.publisher.Flux;

import java.util.List;

public interface ChatService {
        String chat(String query);
        String chatTemplate(String query,String userId);

        Flux<String> streamChat(String query);

        void saveData(List<String> list);

}
