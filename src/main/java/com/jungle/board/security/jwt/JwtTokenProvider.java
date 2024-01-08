package com.jungle.board.security.jwt;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jungle.board.common.WebException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expireMs}")
    private long expireMs;

    private SecretKey secretKey;
    
    @PostConstruct
    protected void init() {
        this.jwtSecret = Base64.getEncoder().encodeToString(jwtSecret.getBytes());
        this.secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String createAccessToken(long userId, String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expireMs);

        return Jwts.builder()
                .subject(Long.toString(userId))
                .claim("email", email)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(secretKey, Jwts.SIG.HS512)
                .compact();
    }

    public Jws<Claims> parseAccessToken(String jws) {
        try {
            return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(jws);
        } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            throw new WebException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new WebException("JWT token has expired");
        }
    }
}
