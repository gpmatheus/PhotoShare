package com.sonego.PhotoShare.common;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JwtUtil {

    public String getUsername(String token);

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDerails);

    public String generateToken(UserDetails userDerails);

    public boolean isTokenValid(String token, UserDetails userDetails);
}
