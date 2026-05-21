package com.aynur.mfa_security_system.controller;

import com.aynur.mfa_security_system.dto.request.LoginRequest;
import com.aynur.mfa_security_system.dto.request.RegisterRequest;
import com.aynur.mfa_security_system.dto.response.ApiResponse;
import com.aynur.mfa_security_system.dto.response.JwtResponse;
import com.aynur.mfa_security_system.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(
            @RequestBody RegisterRequest request
    ) {

        authService.register(request);

        return ResponseEntity.ok(
                new ApiResponse("User registered")
        );
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(
            @RequestBody LoginRequest request
    ) {

        return ResponseEntity.ok(
                authService.login(request)
        );
    }
}