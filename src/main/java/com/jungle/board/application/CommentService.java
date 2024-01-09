package com.jungle.board.application;

import java.util.List;

import com.jungle.board.domain.comment.Comment;

public interface CommentService {
    Comment writeComment(Long userId, Long postId, String content);
    Comment updateComment(Long commentId, String content);
    void deleteComment(Long commentId);
    List<Comment> getAll(Long postId);
}