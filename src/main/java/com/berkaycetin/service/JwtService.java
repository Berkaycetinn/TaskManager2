package com.berkaycetin.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.berkaycetin.entities.User;
import com.berkaycetin.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    private final UserRepository userRepository;

    public JwtService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Value("${security.jwt.secret}")
    private String SECRET_KEY;

    private SecretKey getKey() {
        byte[] key = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(key);
    }

    // ✅ Token üretimi — role bilgisiyle birlikte
    public String generateToken(UserDetails user) {
        String role = user.getAuthorities().stream()
                .findFirst()
                .map(a -> a.getAuthority()) // bu zaten "ROLE_USER" döner
                .orElse("ROLE_USER");

        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("role", role) // ROLE_ prefix ile
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public String findUserName(String token) {
        return extractAllClaims(token).getSubject();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean tokenControl(String token, UserDetails userDetails) {
        String username = findUserName(token);
        Date expiration = extractAllClaims(token).getExpiration();
        return (username.equals(userDetails.getUsername()) && !expiration.before(new Date()));
    }
}
