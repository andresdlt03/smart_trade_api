package com.bluejtitans.smarttradebackend.users.http.login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginSuccess implements LoginResponse{
    private String email;
    private String userType;
}
