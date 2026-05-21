package com.aynur.mfa_security_system.controller;

import com.aynur.mfa_security_system.dto.response.ApiResponse;
import com.aynur.mfa_security_system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse> getMe() {

        return ResponseEntity.ok(
                new ApiResponse(userService.getCurrentUserInfo())
        );
    }
}