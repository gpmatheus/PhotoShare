package com.sonego.PhotoShare.api.model.errorModels;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FieldValidationErrorModel extends ErrorModel {

    private Map<String, String> validationErrors;
}
