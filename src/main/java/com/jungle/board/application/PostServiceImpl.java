package com.jungle.board.application;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jungle.board.common.WebException;
import com.jungle.board.domain.post.Post;
import com.jungle.board.domain.post.PostRepository;
import com.jungle.board.domain.user.UserRepository;

@Service
public class PostServiceImpl implements PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    
    public PostServiceImpl(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public Post writePost(Long userId, String title, String content) {
        var user = userRepository.findById(userId).orElseThrow(() -> {
            throw new WebException("User " + userId + " does not exists");
        });
        
        var now = new java.util.Date().getTime();
        var post = Post.builder()
                       .title(title)
                       .content(content)
                       .createdAt(new Date(now))
                       .updatedAt(new Date(now))
                       .deleted(false)
                       .build();

        post.update(post.getTitle(), post.getContent());
        user.publishPost(post);
        return postRepository.save(post);
    }

    @Override
    public Post getPost(Long postId) {
        var post = postRepository.findById(postId).orElseThrow(() -> {
            throw new WebException("Post" + postId + " does not exists");
        });

        if (post.getDeleted()) {
            throw new WebException("Post" + postId + " does not exists");
        }

        return post;
    }

    @Override
    public Post updatePost(Long postId, String title, String content) {
        var post = postRepository.findById(postId).orElseThrow(() -> {
            throw new WebException("Post" + postId + " dose not exists");
        });

        if (post.getDeleted()) {
            throw new WebException("Post" + postId + " does not exists");
        }

        post.update(title, content);
        postRepository.save(post);
        return post;
    }

    @Override
    public void deletePost(Long postId) {
        var post = postRepository.findById(postId).orElseThrow(() -> {
            throw new WebException("Post" + postId + " dose not exists");
        });

        if (post.getDeleted()) {
            throw new WebException("Post" + postId + " does not exists");
        }

        post.delete();
        postRepository.save(post);
    }

    @Override
    public List<Post> getAll(Long userId) {
        var user = userRepository.findById(userId).orElseThrow(() -> {
            throw new WebException("User" + userId + " dose not exists");
        });

        return user.getPosts();
    }

    @Override
    public List<Post> getAll(String nickname) {
        var user = userRepository.findByNickname(nickname).orElseThrow(() -> {
            throw new WebException("User" + nickname + " dose not exists");
        });

        return user.getPosts();
    }
}
