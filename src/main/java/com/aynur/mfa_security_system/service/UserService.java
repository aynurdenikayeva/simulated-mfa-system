package com.aynur.mfa_security_system.service;

import com.aynur.mfa_security_system.entity.User;
import com.aynur.mfa_security_system.exception.UserNotFoundException;
import com.aynur.mfa_security_system.repository.UserRepository;
import com.aynur.mfa_security_system.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public String getCurrentUserInfo() {

        String email = SecurityUtil.getCurrentUserEmail();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found")
                );

        return "Username: " + user.getUsername()
                + ", Email: " + user.getEmail()
                + ", Role: " + user.getRole();
    }
}