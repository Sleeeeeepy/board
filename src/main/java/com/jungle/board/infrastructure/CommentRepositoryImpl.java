package com.jungle.board.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.jungle.board.domain.comment.Comment;
import com.jungle.board.domain.comment.CommentRepository;
import com.jungle.board.infrastructure.jpa.JpaCommentRepository;

@Component
public class CommentRepositoryImpl implements CommentRepository {

    private final JpaCommentRepository commentRepository;

    public CommentRepositoryImpl(JpaCommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Optional<Comment> findById(long id) {
        return commentRepository.findById(id);
    }

    @Override
    public List<Comment> findAllByPostId(long postId) {
        return commentRepository.findAllByPostId(postId);
    }
}
