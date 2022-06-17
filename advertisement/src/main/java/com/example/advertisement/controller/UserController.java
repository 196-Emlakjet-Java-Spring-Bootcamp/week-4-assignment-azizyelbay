package com.example.advertisement.controller;

import com.example.advertisement.dto.CreateUserRequest;
import com.example.advertisement.dto.UserDto;
import com.example.advertisement.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest request){
        return ResponseEntity.ok(userService.createUser(request));
    }

    @PostMapping("/random-user")
    public ResponseEntity<String> createRandomUsers(){
        return ResponseEntity.ok(userService.createRandomUser());
    }
}
