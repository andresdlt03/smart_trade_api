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
                    userService.saveClient((Client) user);
                    break;
                case "sellers":
                    user = objectMapper.readValue(userJson, Seller.class);
                    userService.saveSeller((Seller) user);
                    break;
                case "admins":
                    user = objectMapper.readValue(userJson, Admin.class);
                    userService.saveAdmin((Admin) user);
                    break;
                default:
                    return ResponseEntity.badRequest().body("Invalid user type");
            }
        } catch(JsonProcessingException e) {
            return ResponseEntity.badRequest().body("Invalid user data: " + e.getMessage());
        } catch(Exception e) {
            return ResponseEntity.badRequest().body("Unknown exception occurred: " + e.getMessage());
        }

        return ResponseEntity.created(null).body(user.getEmail());
    }

}
