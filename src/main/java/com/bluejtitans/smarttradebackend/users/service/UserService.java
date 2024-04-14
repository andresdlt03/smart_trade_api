package com.bluejtitans.smarttradebackend.users.service;

import com.bluejtitans.smarttradebackend.exception.UserAlreadyExistsException;
import com.bluejtitans.smarttradebackend.users.model.IUser;
import com.bluejtitans.smarttradebackend.users.model.User;
import com.bluejtitans.smarttradebackend.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.bluejtitans.smarttradebackend.users.body.LoginCredentials;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<String> saveUser(User user) {

        try {
            if(userRepository.existsById(user.getEmail()))
                throw new UserAlreadyExistsException("User with email " + user.getEmail() + " already exists");
            userRepository.save(user);
        } catch(UserAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch(Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred while registering the user");
        }

        return ResponseEntity.ok(user.getEmail());
    }

    public ResponseEntity<LoginCredentials> loginUser(LoginCredentials loginCredentials) {
        User user = userRepository.findById(loginCredentials.getEmail()).orElse(null);
        if(user == null || !user.getPassword().equals(loginCredentials.getPassword()))
            return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok(loginCredentials);
    }

}
