package com.jungle.board.interfaces.user.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserSignUpResponse {
    private String name;
    private String nickname;
    private String password;
    private String email;
    private Date joinedAt;
}
