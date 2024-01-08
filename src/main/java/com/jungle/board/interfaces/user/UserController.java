package com.jungle.board.interfaces.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jungle.board.application.AuthService;
import com.jungle.board.application.UserService;
import com.jungle.board.interfaces.user.model.UserProfileResponse;
import com.jungle.board.interfaces.user.model.UserSignInRequest;
import com.jungle.board.interfaces.user.model.UserSignInResponse;
import com.jungle.board.interfaces.user.model.UserSignUpRequest;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final AuthService authService;

    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping("/{userId}")
    @ResponseBody
    public ResponseEntity<UserProfileResponse> getUser(@PathVariable("userId") long userId) {
        var user = userService.getUser(userId);
        var response = UserProfileResponse.builder()
                                          .id(user.getId())
                                          .name(user.getName())
                                          .nickname(user.getNickname())
                                          .email(user.getEmail())
                                          .joinedAt(user.getJoinedAt().toString())
                                          .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<UserProfileResponse> signUp(@RequestBody UserSignUpRequest request) {
        var user = UserSignUpRequest.toUser(request);
        authService.hashAndSaltPassword(user);
        var insertedUser = userService.signUp(user);
        var response = UserProfileResponse.builder()
                                          .id(insertedUser.getId())
                                          .name(insertedUser.getName())
                                          .nickname(insertedUser.getNickname())
                                          .email(insertedUser.getEmail())
                                          .joinedAt(insertedUser.getJoinedAt().toString())
                                          .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login/")
    @ResponseBody
    public ResponseEntity<UserSignInResponse> signIn(@RequestBody UserSignInRequest request) {
        var token = this.authService.signIn(request.getNickname(), request.getPassword());
        var response = UserSignInResponse.builder().jwt(token).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
