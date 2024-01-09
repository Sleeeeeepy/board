package com.jungle.board.interfaces.post.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostUpdateRequest {
    private Long userId;
    private Long postId;
    private String title;
    private String content;
    private String jwt;
}
