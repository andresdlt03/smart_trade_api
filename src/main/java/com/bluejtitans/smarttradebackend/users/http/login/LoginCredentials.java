package com.bluejtitans.smarttradebackend.users.http.login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginCredentials {
    private String email;
    private String password;
}
