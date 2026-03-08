package com.collaborativeworkspace.workspace_gateway.security;

import java.security.Key;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtValidator {
    private static final String SECRET_KEY = "MySuperSecretKeyForCollaborativeWorkspace12345!";

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.out.println("Invalid JWT Token: " + e.getMessage());
            return false;
        }
    }
}
