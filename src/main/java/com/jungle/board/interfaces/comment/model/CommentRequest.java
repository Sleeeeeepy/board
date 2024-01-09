package com.jungle.board.interfaces.comment.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {
    private String jwt;
    private Long userId;
    private Long postId;
    private String content;
}
