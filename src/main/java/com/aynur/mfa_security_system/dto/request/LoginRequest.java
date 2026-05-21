package com.aynur.mfa_security_system.dto.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}