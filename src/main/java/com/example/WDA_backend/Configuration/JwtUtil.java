package com.example.WDA_backend.Configuration;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
//    but secrect key to application.yaml
    private final String SECRET = "7aff43c7f83baa7d8638243ab53bc36034fde85e25ec6f5f2cd300907a1d670d";
    private final long EXPIRATION = 1000 * 60 * 60 * 10; // 10 giờ

    public String generateToken(String username, String Id) {
        return Jwts.builder()
                .setSubject(username)
                .claim("Id", Id)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token, String username) {
        String extracted = extractUsername(token);
        return extracted.equals(username) && !isExpired(token);
    }

    private boolean isExpired(String token) {
        Date expiration = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }
}
