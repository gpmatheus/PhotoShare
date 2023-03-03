package com.sonego.PhotoShare.business.model;

public enum ERole {

    ROLE_ADMIN("ROLE_ADMIN"), ROLE_USER("ROLE_USER");

    private final String name;
    ERole(String name) {
        this.name = name;
    }
}
