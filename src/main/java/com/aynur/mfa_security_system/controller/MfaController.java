package com.aynur.mfa_security_system.controller;

import com.aynur.mfa_security_system.dto.request.MfaVerificationRequest;
import com.aynur.mfa_security_system.dto.response.ApiResponse;
import com.aynur.mfa_security_system.entity.User;
import com.aynur.mfa_security_system.enums.MfaAction;
import com.aynur.mfa_security_system.repository.UserRepository;
import com.aynur.mfa_security_system.service.MfaService;
import com.aynur.mfa_security_system.service.NotificationService;
import com.aynur.mfa_security_system.service.OtpService;
import com.aynur.mfa_security_system.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mfa")
@RequiredArgsConstructor
public class MfaController {

    private final UserRepository userRepository;
    private final OtpService otpService;
    private final NotificationService notificationService;
    private final MfaService mfaService;

    @PostMapping("/generate")
    public ResponseEntity<ApiResponse> generateOtp() {

        String email = SecurityUtil.getCurrentUserEmail();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        String otp = otpService.generateOtp(
                user,
                MfaAction.PASSWORD_CHANGE
        );

        notificationService.sendOtp(user.getEmail(), otp);

        return ResponseEntity.ok(
                new ApiResponse("OTP generated")
        );
    }

    @PostMapping("/verify")
    public ResponseEntity<ApiResponse> verifyOtp(
            @RequestBody MfaVerificationRequest request
    ) {

        String email = SecurityUtil.getCurrentUserEmail();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        mfaService.verifyOtp(user, request);

        return ResponseEntity.ok(
                new ApiResponse("MFA verified")
        );
    }
}