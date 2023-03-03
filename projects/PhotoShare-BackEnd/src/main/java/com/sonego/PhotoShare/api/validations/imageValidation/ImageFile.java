package com.sonego.PhotoShare.api.validations.imageValidation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ImageFileValidation.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ImageFile {

    String message() default "invalid image file";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
