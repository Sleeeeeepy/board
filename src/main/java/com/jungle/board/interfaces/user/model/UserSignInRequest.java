package com.jungle.board.interfaces.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignInRequest {
    private String nickname;
    private String password;
}