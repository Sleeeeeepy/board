package com.jungle.board.interfaces.user.model;

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
public class UserProfileResponse {
    private long id;
    private String name;
    private String nickname;
    private String email;
    private String joinedAt;
}
