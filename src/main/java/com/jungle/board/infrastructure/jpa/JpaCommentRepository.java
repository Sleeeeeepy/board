package com.jungle.board.infrastructure.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jungle.board.domain.comment.Comment;

public interface JpaCommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostId(long postId);
}