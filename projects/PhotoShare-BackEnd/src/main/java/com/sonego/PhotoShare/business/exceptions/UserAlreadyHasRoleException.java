package com.sonego.PhotoShare.business.exceptions;

import com.sonego.PhotoShare.business.model.Role;

public class UserAlreadyHasRoleException extends RuntimeException {

    private String username;
    private Role role;

    public UserAlreadyHasRoleException(String username, Role role) {
        this.username = username;
        this.role = role;
    }

    @Override
    public String getMessage() {
        return "User " + username + " has already the role of" +
                this.role.getName().toString().split("_")[1].toLowerCase();
    }
}
