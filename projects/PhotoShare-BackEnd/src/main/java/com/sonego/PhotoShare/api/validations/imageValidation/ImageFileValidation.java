package com.sonego.PhotoShare.api.validations.imageValidation;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.IOException;

public class ImageFileValidation implements ConstraintValidator<ImageFile, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        if (value == null)
            return printResult(false);
        String contentType = value.getContentType();
        if (contentType != null) {
            String firstPart = contentType.split("/")[0];
            return printResult(firstPart.equals("image"));
        }
        return printResult(false);
    }

//    for testing
    private static boolean printResult(boolean result) {
        System.out.println("Está passando pela validação");
        System.out.println(result ? "Está aceitando" : "Está rejeitando");
        return result;
    }
}
