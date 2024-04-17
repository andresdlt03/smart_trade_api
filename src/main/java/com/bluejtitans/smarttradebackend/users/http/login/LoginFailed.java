package com.bluejtitans.smarttradebackend.users.http.login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginFailed implements LoginResponse {
    private String errorMessage = "";
    private String email = "";
}
