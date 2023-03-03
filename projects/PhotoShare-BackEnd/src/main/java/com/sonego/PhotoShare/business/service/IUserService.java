package com.sonego.PhotoShare.business.service;

import com.sonego.PhotoShare.business.model.User;

import java.util.UUID;

public interface IUserService {

    public User getUserById(UUID id);

    public User addAdminPermission(UUID id);

    public User getByUsername(String username);
}
