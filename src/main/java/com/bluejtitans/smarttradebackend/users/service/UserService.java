package com.bluejtitans.smarttradebackend.users.service;

import com.bluejtitans.smarttradebackend.exception.UserAlreadyExistsException;
import com.bluejtitans.smarttradebackend.users.body.Login.LoginCredentials;
import com.bluejtitans.smarttradebackend.users.body.Login.LoginResponse;
import com.bluejtitans.smarttradebackend.users.body.Register.RegisterResponse;
import com.bluejtitans.smarttradebackend.users.model.User;
import com.bluejtitans.smarttradebackend.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<RegisterResponse> saveUser(User user) {

        RegisterResponse response = new RegisterResponse();

        try {
            if(userRepository.existsById(user.getEmail()))
                throw new UserAlreadyExistsException("User with email " + user.getEmail() + " already exists");
            userRepository.save(user);
        } catch(UserAlreadyExistsException e) {
            response.setErrorMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch(Exception e) {
            response.setErrorMessage("An error occurred while saving the user");
            return ResponseEntity.internalServerError().body(response);
        }

        response.setEmail(user.getEmail());
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<LoginResponse> loginUser(LoginCredentials loginCredentials) {

        LoginResponse response = new LoginResponse();

        User user = userRepository.findById(loginCredentials.getEmail()).orElse(null);
        if(user == null || !user.getPassword().equals(loginCredentials.getPassword())) {
            response.setErrorMessage("Invalid email or password");
            return ResponseEntity.badRequest().body(response);
        }

        response.setLoginCredentials(loginCredentials);
        return ResponseEntity.ok(response);
    }
}
