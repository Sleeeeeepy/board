package com.jungle.board.interfaces.post.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {
    private Long userId;
    private String title;
    private String content;
    private String jwt;
}