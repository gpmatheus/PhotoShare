package com.sonego.PhotoShare.api.model.input;

import com.sonego.PhotoShare.api.validations.imageValidation.ImageFile;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ProfileInput {

    @ImageFile
    private MultipartFile profileImage;
    @NotBlank
    private String name;

    @NotBlank
    private String about;
}
