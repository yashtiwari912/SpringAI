package com.yashdev.springai.first.service;

import com.yashdev.springai.first.entity.Blog;
import reactor.core.publisher.Flux;

import java.util.List;

public interface ChatService {
        String getResponse(String userQuery);


        void saveData(List<String> list);

}
