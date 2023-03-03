package com.sonego.PhotoShare.api.model.input;

import com.sonego.PhotoShare.api.validations.imageValidation.ImageFile;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class PostInput {

    @ImageFile
    private MultipartFile image;

    @NotBlank
    private String description;

    @NotNull
    private UUID profileId;

}
