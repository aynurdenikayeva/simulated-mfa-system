package com.aynur.mfa_security_system.repository;

import com.aynur.mfa_security_system.entity.OtpCode;
import com.aynur.mfa_security_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}