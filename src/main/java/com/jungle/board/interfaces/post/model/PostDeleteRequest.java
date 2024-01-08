package com.jungle.board.interfaces.post.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDeleteRequest {
    private Long postId;
    private Long userId;
    private String jwt;
}
