package com.jungle.board.application;

import com.jungle.board.domain.user.User;

public interface AuthService {
    String signIn(String nickname, String password);
    void hashAndSaltPassword(User user);
    void verifyUser(Long userId, String token);
}