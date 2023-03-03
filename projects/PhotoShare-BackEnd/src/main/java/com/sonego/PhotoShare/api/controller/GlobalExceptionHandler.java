package com.sonego.PhotoShare.api.controller;

import com.sonego.PhotoShare.api.model.errorModels.ErrorModel;
import com.sonego.PhotoShare.api.model.errorModels.FieldValidationErrorModel;
import com.sonego.PhotoShare.api.model.errorModels.FileSizeErrorModel;
import com.sonego.PhotoShare.api.wrapper.ProfileWrapper;
import com.sonego.PhotoShare.business.exceptions.BusinessException;
import com.sonego.PhotoShare.business.exceptions.ExistingImageException;
import com.sonego.PhotoShare.business.exceptions.NotFoundException;
import com.sonego.PhotoShare.business.exceptions.UserAlreadyHasRoleException;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler //extends ResponseEntityExceptionHandler
{

    @ExceptionHandler(FileSizeLimitExceededException.class)
    public ResponseEntity<FileSizeErrorModel> handleFileSizeLimitExceededException(FileSizeLimitExceededException ex) {
        FileSizeErrorModel error = new FileSizeErrorModel();
        error.setErrorType("File size");
        error.setErrorMessage("The image file is too big");
        error.setPermittedSize(ex.getPermittedSize());
        error.setFileSize(ex.getActualSize());
        return ResponseEntity.status(HttpStatus.INSUFFICIENT_STORAGE).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<FieldValidationErrorModel> handleFieldValidation(MethodArgumentNotValidException ex) {
        FieldValidationErrorModel error = handleFieldValidationProblems(ex.getAllErrors());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<FieldValidationErrorModel> handleBindException(BindException ex) {
        FieldValidationErrorModel error = handleFieldValidationProblems(ex.getAllErrors());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    private FieldValidationErrorModel handleFieldValidationProblems(List<ObjectError> errors) {
        var error = new FieldValidationErrorModel();
        error.setErrorType("Field validation");
        error.setErrorMessage(errors.size() <= 1 ? "Invalid field" : "Invalid fields");
        Map<String, String> errorMap = new HashMap<>();
        errors.stream().forEach(er -> {
            FieldError fieldError = (FieldError) er;
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        error.setValidationErrors(errorMap);
        return error;
    }

    @ExceptionHandler(UserAlreadyHasRoleException.class)
    public ResponseEntity<ErrorModel> handleUserAlreadyHasRoleException(UserAlreadyHasRoleException ex) {
        ErrorModel error = new ErrorModel();
        error.setErrorType("No change");
        error.setErrorMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(ExistingImageException.class)
    public ResponseEntity<ErrorModel> handleExistingImageException(ExistingImageException ex) {
        ErrorModel error = new ErrorModel();
        error.setErrorType("Business exception");
        error.setErrorMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorModel> handleBusinessException(BusinessException ex) {
        ErrorModel error = new ErrorModel();
        error.setErrorType("Business exception");
        error.setErrorMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorModel> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(handleNotFoundProblems(ex.getMessage()));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorModel> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(handleNotFoundProblems(ex.getMessage()));
    }

    private ErrorModel handleNotFoundProblems(String message) {
        ErrorModel error = new ErrorModel();
        error.setErrorType("Resource not found");
        error.setErrorMessage(message);
        return error;
    }
}
