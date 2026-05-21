package com.aynur.mfa_security_system.service;

import com.aynur.mfa_security_system.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtUtil jwtUtil;

    public String generateToken(String email) {
        return jwtUtil.generateToken(email);
    }

    public String extractEmail(String token) {
        return jwtUtil.extractEmail(token);
    }

    public boolean isValid(String token) {
        return jwtUtil.isTokenValid(token);
    }
}