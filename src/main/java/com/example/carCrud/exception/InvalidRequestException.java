package com.example.carCrud.exception;

import org.springframework.validation.FieldError;
import org.springframework.validation.BindingResult;

import java.util.List;

public class InvalidRequestException extends RuntimeException {

    private final List<FieldError> fieldErrors;

    public InvalidRequestException(BindingResult bindingResult) {
        super("Invalid request: " + bindingResult.getFieldErrors());
        this.fieldErrors = bindingResult.getFieldErrors();
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }
}