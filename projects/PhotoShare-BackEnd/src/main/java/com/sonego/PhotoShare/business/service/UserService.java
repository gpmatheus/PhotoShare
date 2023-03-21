package com.sonego.PhotoShare.business.service;

import com.sonego.PhotoShare.business.exceptions.*;
import com.sonego.PhotoShare.business.model.ERole;
import com.sonego.PhotoShare.business.model.Role;
import com.sonego.PhotoShare.business.model.User;
import com.sonego.PhotoShare.persistence.repository.RoleRepository;
import com.sonego.PhotoShare.persistence.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User of id " +
                        id + " could not be found"));
    }

    @Override
    @Transactional
    public User addAdminPermission(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User of id " +
                        id + " could not be found"));
        Role role = roleRepository.findByName(ERole.ROLE_ADMIN)
                .orElse(new Role(ERole.ROLE_ADMIN));
        if (user.getRoles().contains(role))
            throw new UserAlreadyHasRoleException(user.getUsername(), role);
        user.addRole(role);
        return user;
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User of username " +
                        username + " could not be found"));
    }
}
