package com.example.learn_handling_exceptions.Exceptions;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {

    private String message;
    List<String> errors = new ArrayList<>();

    public ErrorResponse(String message, List<String> details) {
        this.message = message;
        this.errors = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
