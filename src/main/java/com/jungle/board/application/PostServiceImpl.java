package com.jungle.board.application;

import org.springframework.stereotype.Service;

import com.jungle.board.common.WebException;
import com.jungle.board.domain.post.Post;
import com.jungle.board.domain.post.PostRepository;
import com.jungle.board.domain.user.UserRepository;
import com.jungle.board.security.xss.PostParser;

@Service
public class PostServiceImpl implements PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final PostParser postParser;
    
    public PostServiceImpl(UserRepository userRepository, PostRepository postRepository, PostParser postParser) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.postParser = postParser;
    }

    @Override
    public Post writePost(Long userId, Post post) {
        var user = userRepository.findById(userId).orElseThrow(() -> {
            throw new WebException("User " + userId + " does not exists");
        });
        
        post.update(post.getTitle(), postParser.filterContent(post.getContent()));
        post.setThumbnailUrl(postParser.fetchThumbnail(post.getContent()));
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
    public Post updatePost(Long userId, Post post) {
        if (post.getAuthor().getId() != userId) {
            throw new WebException("Post can't be updated by others");
        }

        if (post.getDeleted()) {
            throw new WebException("Post" + post.getId() + " does not exists");
        }

        post.update(post.getTitle(), postParser.filterContent(post.getContent()));
        post.setThumbnailUrl(postParser.fetchThumbnail(post.getContent()));
        postRepository.save(post);
        return post;
    }

    @Override
    public void deletePost(Long userId, Post post) {
        if (post.getAuthor().getId() != userId) {
            throw new WebException("Post can't be deleted by others");
        }

        post.delete();
    }
}
