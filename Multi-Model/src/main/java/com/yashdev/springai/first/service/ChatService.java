package com.yashdev.springai.first.service;

import com.yashdev.springai.first.entity.Blog;

import java.util.List;

public interface ChatService {
        List<Blog> chat(String query);
}
