package com.bluejtitans.smarttradebackend.users.controller.http.register;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterFailed implements RegisterResponse {
    private String errorMessage = "";
    private String email = "";
}
