package com.jungle.board.application;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jungle.board.common.WebException;
import com.jungle.board.domain.user.User;
import com.jungle.board.domain.user.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User signUp(User user) {
        userRepository.findByNickname(user.getNickname()).ifPresent((u)->{
            throw new WebException(HttpStatus.BAD_REQUEST, "User " + u.getNickname() + " already exists");
        });

        return userRepository.save(user);
    }

    @Override
    public User getUser(long id) {
        var user = userRepository.findById(id).orElseThrow(() -> {
            throw new WebException(HttpStatus.NOT_FOUND, "User " + id + " dose not exists");
        });

        return user;
    }
}
