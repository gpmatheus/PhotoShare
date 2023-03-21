package com.sonego.PhotoShare.api.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
public class CommentModel {

    private UUID id;

    private String content;

    private OffsetDateTime commentingDate;

    private UUID commenterId;

    private String commenterUsername;
}
