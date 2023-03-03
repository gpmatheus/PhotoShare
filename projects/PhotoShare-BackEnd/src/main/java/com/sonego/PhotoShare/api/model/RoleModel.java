package com.sonego.PhotoShare.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sonego.PhotoShare.business.model.ERole;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleModel {

    private UUID id;

    private ERole name;
}
