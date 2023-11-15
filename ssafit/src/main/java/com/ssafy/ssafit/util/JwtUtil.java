package com.ssafy.ssafit.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {
    private static final String SALT = generateRandomSalt();

    public String createToken(String key, Long value) {
        return Jwts.builder()
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JWT")
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1시간 후 만료
                .claim(key, value)
                .signWith(SignatureAlgorithm.HS256, SALT.getBytes(StandardCharsets.UTF_8))
                .compact();
    }

    public void validate(String token) {
        Jwts.parser().setSigningKey(SALT.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token);
    }

    private static String generateRandomSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
}
