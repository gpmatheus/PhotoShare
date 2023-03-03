package com.sonego.PhotoShare.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserModel {

    private UUID id;

    private String email;

    private String username;

    private List<ProfileModel> profiles;

    private List<RoleModel> roles;

    private String token;
}
