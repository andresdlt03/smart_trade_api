package com.bluejtitans.smarttradebackend.users.controller.http.login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginCredentials {
    private String email;
    private String password;
}
