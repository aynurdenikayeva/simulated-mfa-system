package com.aynur.mfa_security_system.dto.request;

import com.aynur.mfa_security_system.enums.MfaAction;
import lombok.Data;

@Data
public class MfaVerificationRequest {
    private String code;
    private MfaAction action;
}
