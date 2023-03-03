package com.sonego.PhotoShare.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DescriptionModel {

    private UUID id;

    private String content;
    private OffsetDateTime editingDateTime;
}
