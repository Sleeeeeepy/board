package com.jungle.board.infrastructure;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.jungle.board.domain.user.User;
import com.jungle.board.infrastructure.jpa.JpaUserRepository;
import com.jungle.board.domain.user.UserRepository;

@Component
public class UserRepositoryImpl implements UserRepository {
    private final JpaUserRepository userRepository;

    public UserRepositoryImpl(JpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findById(long id) {
        var user = userRepository.findById(id);
        return user;
    }

    @Override
    public Optional<User> findByNickname(String nickname) {
        var user = userRepository.findByNickname(nickname);
        return user;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}