package com.jungle.board.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jungle.board.domain.post.Post;

public interface JpaPostRepository extends JpaRepository<Post, Long> {
    
}