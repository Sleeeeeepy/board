package com.jungle.board.domain.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(long id);
    Optional<User> findByNickname(String nickname);
    List<User> findAll();
    User save(User user);
}