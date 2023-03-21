package com.sonego.PhotoShare.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sonego.PhotoShare.business.model.Post;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileModel {

    private UUID id;

    private String name;

    private String about;

    private String profileImageUrl;

    private List<PostModel> posts = new ArrayList<>();

    private UUID ownerId;
}
