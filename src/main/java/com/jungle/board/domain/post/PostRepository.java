package com.jungle.board.domain.post;

import java.util.Optional;

public interface PostRepository {
    Optional<Post> findById(long id);
    Post save(Post post);
}