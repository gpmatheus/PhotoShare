package com.sonego.PhotoShare.business.service;

import com.sonego.PhotoShare.business.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface IAuthenticationService {

    public String authenticate(String username, String password);

    public User register(User user);

    public boolean isTokenValid(String token, String username);
}
