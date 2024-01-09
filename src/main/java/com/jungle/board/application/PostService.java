package com.jungle.board.application;

import java.util.List;

import com.jungle.board.domain.post.Post;

public interface PostService {
    Post writePost(Long userId, String title, String content);
    Post getPost(Long postId);
    Post updatePost(Long postId, String title, String content);
    void deletePost(Long postId);
    List<Post> getAll(Long userId);
    List<Post> getAll(String nickname);
}