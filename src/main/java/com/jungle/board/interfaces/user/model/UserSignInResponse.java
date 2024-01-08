package com.jungle.board.interfaces.user.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserSignInResponse {
    private String jwt;
}