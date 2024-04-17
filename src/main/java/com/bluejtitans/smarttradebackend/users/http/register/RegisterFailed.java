package com.bluejtitans.smarttradebackend.users.http.register;

import lombok.Getter;
import lombok.Setter;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Getter
@Setter
public class RegisterFailed implements RegisterResponse {
    private String errorMessage = "";
    private String email = "";
}
