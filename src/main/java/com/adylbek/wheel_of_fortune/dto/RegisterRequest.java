package com.adylbek.wheel_of_fortune.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String login;
    private String password;
    private String role;
}