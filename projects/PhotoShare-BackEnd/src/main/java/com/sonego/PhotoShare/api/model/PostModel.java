package com.sonego.PhotoShare.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostModel {

    private UUID id;
    private String imageUrl;
    private OffsetDateTime postingDate;
    private List<DescriptionModel> descriptions = new ArrayList<>();

    private List<CommentModel> comments = new ArrayList<>();

    private ProfileModel profile;
}
