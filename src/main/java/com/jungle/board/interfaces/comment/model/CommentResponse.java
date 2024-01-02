package com.jungle.board.interfaces.comment.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CommentResponse {
    private int postId;
    private int userId;
    private String body;
    private Date updatedAt;
    private Date createdAt;
}
