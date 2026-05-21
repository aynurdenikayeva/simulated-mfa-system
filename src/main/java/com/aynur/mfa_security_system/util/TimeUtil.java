package com.aynur.mfa_security_system.util;

import java.time.LocalDateTime;

public class TimeUtil {

    public static boolean isExpired(LocalDateTime expiryTime) {

        return LocalDateTime.now().isAfter(expiryTime);
    }

    public static LocalDateTime addMinutes(int minutes) {

        return LocalDateTime.now().plusMinutes(minutes);
    }
}