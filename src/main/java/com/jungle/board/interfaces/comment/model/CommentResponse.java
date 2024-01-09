package com.jungle.board.interfaces.comment.model;

import java.util.Date;

import com.jungle.board.domain.comment.Comment;

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
    private Long postId;
    private Long userId;
    private String nickname;
    private Long commentId;
    private String content;
    private Date updatedAt;
    private Date createdAt;

    public static CommentResponse fromComment(Comment comment) {
        return CommentResponse.builder()
                       .postId(comment.getPost().getId())
                       .userId(comment.getAuthor().getId())
                       .nickname(comment.getAuthor().getNickname())
                       .commentId(comment.getId())
                       .content(comment.getContent())
                       .updatedAt(comment.getUpdatedAt())
                       .createdAt(comment.getCreatedAt())
                       .build();
    }
}
