package com.aynur.mfa_security_system.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    /**
     * Secret key yaratmaq
     */
    private SecretKey getSigningKey() {

        return Keys.hmacShaKeyFor(
                secretKey.getBytes(StandardCharsets.UTF_8)
        );
    }

    /**
     * JWT token yaratmaq
     */
    public String generateToken(String email) {

        Date now = new Date();

        Date expiryDate =
                new Date(now.getTime() + jwtExpiration);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(
                        getSigningKey(),
                        SignatureAlgorithm.HS256
                )
                .compact();
    }

    /**
     * Token içindən email çıxarmaq
     */
    public String extractEmail(String token) {

        return extractAllClaims(token)
                .getSubject();
    }

    /**
     * Token expiration yoxlamaq
     */
    public boolean isTokenValid(String token) {

        return !isTokenExpired(token);
    }

    /**
     * Token expired olub olmadığını yoxlamaq
     */
    private boolean isTokenExpired(String token) {

        Date expirationDate =
                extractAllClaims(token)
                        .getExpiration();

        return expirationDate.before(new Date());
    }

    /**
     * Token içindəki bütün məlumatları çıxarmaq
     */
    private Claims extractAllClaims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}