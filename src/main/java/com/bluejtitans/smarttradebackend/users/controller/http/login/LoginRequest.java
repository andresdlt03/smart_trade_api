package com.bluejtitans.smarttradebackend.users.controller.http.login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private LoginCredentials credentials;
}
