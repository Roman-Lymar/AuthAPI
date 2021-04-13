package com.springboot.authapiproject.exceptions;

public enum ErrorMessages {

    NO_RESOURCE_FOUND("Resource with provided id is not found: id="),
    NO_RESOURCE_FOUND_BY_NAME("No results returned by the Query. No matches with: "),
    INVALID_TOKEN(" Token is missing or invalid"),
    INVALID_ROLE("Non-existent Role"),
    NOT_FOUND_USER("User not found"),
    USER_EXISTS("User already exists"),
    INCORRECT("Username or password is incorrect"),
    EMPTY_FIELD("Field shouldn't be empty");


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
