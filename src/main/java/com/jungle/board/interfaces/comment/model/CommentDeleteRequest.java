package com.jungle.board.interfaces.comment.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDeleteRequest {
    private Long commentId;
    private Long userId;
    private String jwt;
}
