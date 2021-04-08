package com.springboot.authapiproject.exceptions;

public enum ErrorMessages {

    NO_RESOURCE_FOUND("Resource with provided id is not found: id="),
    NO_RESOURCE_FOUND_BY_NAME("No results returned by the Query. No matches with: "),
    EMPTY_PACKAGE("Package does not have any products. Please, add some!"),
    UNABLE_DELETE_PACKAGE("This products is used by packages");

    /* BAD_PASSWORD("The entered password does not meet the password policy requirements." +
        "1. Password must contain at least one digit [0-9].\n" +
        "2. Password must contain at least one lowercase Latin character [a-z].\n" +
        "3. Password must contain at least one uppercase Latin character [A-Z].\n" +
        "4. Password must contain at least one special character like ! @ # & ( ).\n" +
        "5. Password must contain a length of at least 8 characters and a maximum of 20 characters.");
*/

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
