package com.aynur.mfa_security_system.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void sendOtp(String email, String otp) {
        System.out.println("========================");
        System.out.println("OTP SENT TO: " + email);
        System.out.println("OTP: " + otp);
        System.out.println("========================");
    }
}