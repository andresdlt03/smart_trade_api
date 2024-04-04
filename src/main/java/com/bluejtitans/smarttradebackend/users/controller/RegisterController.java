package com.bluejtitans.smarttradebackend.users.controller;

import com.bluejtitans.smarttradebackend.users.model.*;
import com.bluejtitans.smarttradebackend.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class RegisterController {

    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/{userType}")
    public ResponseEntity<String> registerUser(@PathVariable String userType, @RequestBody String userJson) {
        try {
            IUser user = UserFactory.createUserFromJson(userType, userJson);
            user = userService.registerUser(user);
            return ResponseEntity.ok(user.getEmail());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
