package com.yashdev.springai.first.entity;

import lombok.Data;


@Data
public class Blog {
    private String title;
    private String content;
    private String createdAt;
}
