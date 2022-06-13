package com.nazar.backendspringboot.security;

import com.nazar.backendspringboot.dao.user.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public final class TokenGenerator {

    public static final String SECRET = "nazar";

    private TokenGenerator() {
    }

    public static String generate(UserEntity user) {
        Claims claims = Jwts.claims();
        claims.setSubject(user.getLogin());
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setClaims(claims)
                .compact();
    }
}
