package com.aynur.mfa_security_system.mapper;

import com.aynur.mfa_security_system.dto.response.ApiResponse;
import com.aynur.mfa_security_system.entity.User;

public class UserMapper {

    public static ApiResponse toResponse(User user) {

        return ApiResponse.builder()
                .message(
                        "User: " + user.getUsername() +
                                ", Email: " + user.getEmail() +
                                ", Role: " + user.getRole()
                )
                .build();
    }
}