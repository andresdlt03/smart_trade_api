package com.bluejtitans.smarttradebackend.users.body.Register;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponse {
    private String errorMessage = "";
    private String email = "";
}
