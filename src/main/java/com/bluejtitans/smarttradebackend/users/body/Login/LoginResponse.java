package com.bluejtitans.smarttradebackend.users.body.Login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private LoginCredentials loginCredentials = null;
    private String errorMessage = "";
}
