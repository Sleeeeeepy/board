package com.jungle.board.interfaces.room;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jungle.board.application.AuthService;
import com.jungle.board.application.UserService;
import com.jungle.board.interfaces.room.model.MakingRoomRequest;
import com.jungle.board.interfaces.room.model.RoomResponse;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/rooms")
public class RoomController {
    private final UserService userService;
    private final AuthService authService;

    public RoomController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<List<RoomResponse>> getRooms() {
        var users = userService.getAllUser();
        var rooms = users.stream()
             .filter(user -> user.getRoomPosition() != null)
             .map(user -> RoomResponse.fromUser(user))
             .collect(Collectors.toList());
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<RoomResponse> setRoom(@RequestBody MakingRoomRequest request) {
        var user =  userService.setRoom(request.getUserId(), request.getX(), request.getY(), request.getZ());
        var response = RoomResponse.builder()
                                   .userId(user.getId())
                                   .nickname(user.getNickname())
                                   .x(user.getRoomPosition().getX())
                                   .y(user.getRoomPosition().getY())
                                   .z(user.getRoomPosition().getZ())
                                   .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
