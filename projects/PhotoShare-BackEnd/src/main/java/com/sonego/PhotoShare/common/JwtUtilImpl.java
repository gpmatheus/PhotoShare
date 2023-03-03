package com.sonego.PhotoShare.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtilImpl implements JwtUtil {

    private static final String SIGNIN_KEY = "7134743777217A25432A462D4A614E645267556A586E3272357538782F413F44";

    private static final long EXPIRATION_TIME = 1000 * 60 * 24;

    @Override
    public String getUsername(String token) {
        try {
            Claims claims = getClaims(token);
            return claims.getSubject();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDerails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDerails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String generateToken(UserDetails userDerails) {
        return generateToken(new HashMap<>(), userDerails);
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        try {
            String tokenUsername = getUsername(token);
            return (tokenUsername.equals(userDetails.getUsername())) &&
                    getClaims(token).getExpiration().after(new Date());

        } catch (Exception ex) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SIGNIN_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
