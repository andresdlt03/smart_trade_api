package com.bluejtitans.smarttradebackend.users.controller;

import com.bluejtitans.smarttradebackend.users.model.*;
import com.bluejtitans.smarttradebackend.users.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        ObjectMapper objectMapper = new ObjectMapper();
        IUser user;

        try {
            switch(userType) {
                case "clients":
                    user = objectMapper.readValue(userJson, Client.class);
                    break;
                case "sellers":
                    user = objectMapper.readValue(userJson, Seller.class);
                    break;
                case "admins":
                    user = objectMapper.readValue(userJson, Admin.class);
                    break;
                default:
                    return ResponseEntity.badRequest().body("Invalid user type");
            }
        } catch(JsonProcessingException e) {
            return ResponseEntity.badRequest().body("Invalid user data");
        }

        // Save user to database
        IUser res = userService.saveUser(userType, user);

        return ResponseEntity.created(null).body(res.getEmail());
    }

}
