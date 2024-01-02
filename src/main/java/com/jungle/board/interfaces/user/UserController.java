package com.jungle.board.interfaces.user;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jungle.board.interfaces.user.model.UserProfileResponse;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/{userId}")
    @ResponseBody
    public ResponseEntity<UserProfileResponse> getUser(@PathVariable("userId") long userId) {
        var response = new UserProfileResponse(1, "Jeongmin", "Sleeeeeepy", "Sleeeeeepy@github.io", new Date());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
