package com.jungle.board.domain.comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Optional<Comment> findById(long id);
    List<Comment> findAllByPostId(long postId);
}