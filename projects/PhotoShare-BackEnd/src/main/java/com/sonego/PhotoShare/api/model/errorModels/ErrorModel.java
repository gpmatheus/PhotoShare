package com.sonego.PhotoShare.api.model.errorModels;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorModel {

    private String errorType;

    private OffsetDateTime timestamp;

    private String errorMessage;

    public ErrorModel() {
        this.timestamp = OffsetDateTime.now();
    }
}
