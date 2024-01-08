package com.jungle.board.application;

import com.jungle.board.domain.post.Post;

public interface PostService {
    Post writePost(Long userId, Post post);
    Post getPost(Long postId);
    Post updatePost(Long userId, Post post);
    void deletePost(Long userId, Post post);
}