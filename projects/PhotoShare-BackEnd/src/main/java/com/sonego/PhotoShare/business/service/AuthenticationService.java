package com.sonego.PhotoShare.business.service;

import com.sonego.PhotoShare.business.exceptions.BusinessException;
import com.sonego.PhotoShare.business.model.ERole;
import com.sonego.PhotoShare.business.model.Role;
import com.sonego.PhotoShare.business.model.User;
import com.sonego.PhotoShare.common.JwtUtil;
import com.sonego.PhotoShare.persistence.repository.RoleRepository;
import com.sonego.PhotoShare.persistence.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AuthenticationService implements IAuthenticationService {

    private JwtUtil jwtUtil;

    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    private UserService userService;

    private UserDetailsService userDetailsService;

    private RoleRepository roleRepository;

    @Override
    public String authenticate(String username, String password) {
        UserDetails userDetails = userService.getByUsername(username);
        return jwtUtil.generateToken(userDetails);
    }


    @Override
    @Transactional
    public User register(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new BusinessException("User with email " + user.getEmail() + " already exists");
        }
        if (userRepository.existsByUsername(user.getUsername()))
            throw new BusinessException("User with username " + user.getUsername() + " already exists");
        user.addRole(roleRepository.findByName(ERole.ROLE_USER)
                .orElse(new Role(ERole.ROLE_USER)));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public boolean isTokenValid(String token, String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return jwtUtil.isTokenValid(token, userDetails);
    }
}
