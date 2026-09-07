package com.adylbek.wheel_of_fortune.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String login;
    private String password;
}