package com.jungle.board.application;

import java.util.List;

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

    @Override
    public User getUser(String nickname) {
        var user = userRepository.findByNickname(nickname).orElseThrow(() -> {
            throw new WebException(HttpStatus.NOT_FOUND, "User " + nickname + " dose not exists");
        });

        return user;
    }

    @Override
    public List<User> getAllUser() {
        var users = userRepository.findAll();
        return users;
    }

    @Override
    public User setRoom(Long userId, double x, double y, double z) {
         var user = userRepository.findById(userId).orElseThrow(() -> {
            throw new WebException(HttpStatus.NOT_FOUND, "User " + userId + " dose not exists");
        });

        user.changeRoomPosition(x, y, z);
        userRepository.save(user);
        return user;
    }
}
