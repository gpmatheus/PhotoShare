package com.sonego.PhotoShare.api.model.errorModels;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileSizeErrorModel extends ErrorModel {

    private long permittedSize;

    private long fileSize;
}
