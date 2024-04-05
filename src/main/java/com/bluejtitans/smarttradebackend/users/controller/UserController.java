package com.bluejtitans.smarttradebackend.users.controller;

import com.bluejtitans.smarttradebackend.exception.UserAlreadyExistsException;
import com.bluejtitans.smarttradebackend.users.model.Client;
import com.bluejtitans.smarttradebackend.users.model.IUser;
import com.bluejtitans.smarttradebackend.users.model.User;
import com.bluejtitans.smarttradebackend.users.model.UserFactory;
import com.bluejtitans.smarttradebackend.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/{userType}")
    public ResponseEntity<String> registerUser(@RequestBody String userJson, @PathVariable String userType) {
        userType = userType.substring(0, userType.length() - 1);
        User user = (User) UserFactory.createUserFromJson(userType, userJson);
        return userService.saveUser(user);
    }

}