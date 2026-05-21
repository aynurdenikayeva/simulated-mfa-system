package com.aynur.mfa_security_system.service;

import com.aynur.mfa_security_system.dto.request.LoginRequest;
import com.aynur.mfa_security_system.dto.request.RegisterRequest;
import com.aynur.mfa_security_system.dto.response.JwtResponse;
import com.aynur.mfa_security_system.entity.User;
import com.aynur.mfa_security_system.enums.Role;
import com.aynur.mfa_security_system.repository.UserRepository;
import com.aynur.mfa_security_system.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public void register(RegisterRequest request) {

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        userRepository.save(user);
    }

    public JwtResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return JwtResponse.builder()
                .token(token)
                .build();
    }
}