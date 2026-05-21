package com.aynur.mfa_security_system.repository;

import com.aynur.mfa_security_system.entity.OtpCode;
import com.aynur.mfa_security_system.entity.User;
import com.aynur.mfa_security_system.enums.MfaAction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<OtpCode, Long> {
    Optional<OtpCode> findTopByUserAndActionOrderByIdDesc(
            User user,
            MfaAction action
    );
}