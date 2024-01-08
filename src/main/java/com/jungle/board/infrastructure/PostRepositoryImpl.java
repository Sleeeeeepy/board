package com.jungle.board.infrastructure;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.jungle.board.domain.post.Post;
import com.jungle.board.domain.post.PostRepository;
import com.jungle.board.infrastructure.jpa.JpaPostRepository;

@Component
public class PostRepositoryImpl implements PostRepository {
    private final JpaPostRepository postRepository;

    public PostRepositoryImpl(JpaPostRepository postRepository) {
        this.postRepository = postRepository;
    }
    
    @Override
    public Optional<Post> findById(long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }
}
