package com.jungle.board.interfaces.user.model;

import java.util.Date;

import com.jungle.board.domain.user.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignUpRequest {
    private String name;
    private String nickname;
    private String email;
    private String password;

    public static User toUser(UserSignUpRequest request) {
        var time = new java.sql.Date(new Date().getTime());
        return User.builder()
                   .name(request.name)
                   .nickname(request.nickname)
                   .password(request.password)
                   .email(request.email)
                   .joinedAt(time)
                   .build();
    }
}