package com.jungle.board.infrastructure.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jungle.board.domain.user.User;

public interface JpaUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNickname(String nickname);
}