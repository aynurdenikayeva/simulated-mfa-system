package com.aynur.mfa_security_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class OtpCode {

    @Id
    private Long id;

    private String code;

    private Long userId;

    private String action;

    private LocalDateTime expiresAt;

    private boolean used;

    private int attempts;
}