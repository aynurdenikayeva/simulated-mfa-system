package com.aynur.mfa_security_system.service;

import com.aynur.mfa_security_system.dto.request.MfaVerificationRequest;
import com.aynur.mfa_security_system.entity.OtpCode;
import com.aynur.mfa_security_system.entity.User;
import com.aynur.mfa_security_system.exception.InvalidOtpException;
import com.aynur.mfa_security_system.exception.OtpExpiredException;
import com.aynur.mfa_security_system.exception.TooManyAttemptsException;
import com.aynur.mfa_security_system.repository.OtpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MfaService {

    private final OtpRepository otpRepository;

    public void verifyOtp(User user, MfaVerificationRequest request) {

        OtpCode otpCode = otpRepository
                .findTopByUserAndActionOrderByIdDesc(
                        user,
                        request.getAction()
                )
                .orElseThrow(() ->
                        new InvalidOtpException("OTP not found")
                );

        if (otpCode.isUsed()) {
            throw new InvalidOtpException("OTP already used");
        }

        if (LocalDateTime.now().isAfter(otpCode.getExpiresAt())) {
            throw new OtpExpiredException("OTP expired");
        }

        if (otpCode.getAttempts() >= 5) {
            throw new TooManyAttemptsException("Too many attempts");
        }

        if (!otpCode.getCode().equals(request.getCode())) {

            otpCode.setAttempts(otpCode.getAttempts() + 1);

            otpRepository.save(otpCode);

            throw new InvalidOtpException("Invalid OTP");
        }

        otpCode.setUsed(true);

        otpRepository.save(otpCode);
    }
}