package com.jungle.board.application;

import java.util.List;

import com.jungle.board.domain.user.User;

public interface UserService {
    User signUp(User user);
    User getUser(long id);
    User getUser(String nickname);
    List<User> getAllUser();
    User setRoom(Long userId, double x, double y, double z);
}