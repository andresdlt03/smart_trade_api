package com.bluejtitans.smarttradebackend.users.service;

import com.bluejtitans.smarttradebackend.exception.UserAlreadyExistsException;
import com.bluejtitans.smarttradebackend.users.http.login.*;
import com.bluejtitans.smarttradebackend.users.http.register.RegisterFailed;
import com.bluejtitans.smarttradebackend.users.http.register.RegisterResponse;
import com.bluejtitans.smarttradebackend.users.http.register.RegisterSuccess;
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
        RegisterFailed registerFailed = new RegisterFailed();
        registerFailed.setEmail(user.getEmail());
        try {
            if(userRepository.existsById(user.getEmail()))
                throw new UserAlreadyExistsException("Ya existe un usuario con el correo " + user.getEmail());
            userRepository.save(user);
        } catch(UserAlreadyExistsException e) {
            registerFailed.setErrorMessage(e.getMessage());
            return ResponseEntity.badRequest().body(registerFailed);
        } catch(Exception e) {
            registerFailed.setErrorMessage("Un error desconocido ha ocurrido al registrar el usuario. Inténtelo de nuevo mas tarde");
            return ResponseEntity.internalServerError().body(registerFailed);
        }
        RegisterSuccess registerSuccess = new RegisterSuccess();
        registerSuccess.setEmail(user.getEmail());
        return ResponseEntity.ok(registerSuccess);
    }

    public ResponseEntity<LoginResponse> loginUser(LoginRequest loginRequest) {
        LoginFailed loginFailed = new LoginFailed();
        LoginCredentials loginCredentials = loginRequest.getCredentials();
        loginFailed.setEmail(loginCredentials.getEmail());

        User user = userRepository.findById(loginCredentials.getEmail()).orElse(null);
        if(user == null || !user.getPassword().equals(loginCredentials.getPassword())) {
            loginFailed.setErrorMessage("Credenciales incorrectas. Inténtelo de nuevo.");
            return ResponseEntity.badRequest().body(loginFailed);
        }

        LoginSuccess loginSuccess = new LoginSuccess();
        loginSuccess.setEmail(loginCredentials.getEmail());
        return ResponseEntity.ok(loginSuccess);
    }
}
