package com.aynur.mfa_security_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Role {

    @Id
    private Long id;

    private String name;
}
