package com.jungle.board.interfaces.room.model;

import com.jungle.board.domain.user.User;

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
public class RoomResponse {
    private long userId;
    private String nickname;
    private double x;
    private double y;
    private double z;

    public static RoomResponse fromUser(User user) {
        return RoomResponse.builder()
                           .userId(user.getId())
                           .nickname(user.getNickname())
                           .x(user.getRoomPosition().getX())
                           .y(user.getRoomPosition().getY())
                           .z(user.getRoomPosition().getZ())
                           .build();
    }
}