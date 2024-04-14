package com.bluejtitans.smarttradebackend.users.body;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginCredentials {
    private String email;
    private String password;

    public LoginCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
