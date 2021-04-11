package com.springboot.authapiproject.exceptions;

public enum ErrorMessages {

    NO_RESOURCE_FOUND("Resource with provided id is not found: id="),
    NO_RESOURCE_FOUND_BY_NAME("No results returned by the Query. No matches with: "),
    IVALID_TOKEN(" Token is missing or invalid");


    private String errorMessage;

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
