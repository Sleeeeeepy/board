package com.jungle.board.domain.user;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomPosition {
    @Column(name = "room_x")
    private double x;

    @Column(name = "room_y")
    private double y;

    @Column(name = "room_z")
    private double z;
}