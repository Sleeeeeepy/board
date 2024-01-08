package com.jungle.board.application;

import com.jungle.board.domain.user.User;

public interface UserService {
    User signUp(User user);
    User getUser(long id);
}