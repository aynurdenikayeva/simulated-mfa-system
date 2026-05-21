package com.aynur.mfa_security_system.service;

import com.aynur.mfa_security_system.entity.OtpCode;
import com.aynur.mfa_security_system.entity.User;
import com.aynur.mfa_security_system.enums.MfaAction;
import com.aynur.mfa_security_system.repository.OtpRepository;
import com.aynur.mfa_security_system.util.OtpGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OtpService {

    private final OtpRepository otpRepository;

    public String generateOtp(User user, MfaAction action) {

        String otp = OtpGenerator.generateOtp();

        OtpCode otpCode = OtpCode.builder()
                .code(otp)
                .used(false)
                .attempts(0)
                .expiresAt(LocalDateTime.now().plusMinutes(5))
                .user(user)
                .action(action)
                .build();

        otpRepository.save(otpCode);

        return otp;
    }
}