package com.jungle.board.interfaces.room.model;

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
public class MakingRoomRequest {
    private long userId;
    private String nickname;
    private double x;
    private double y;
    private double z;
    private String jwt;
}