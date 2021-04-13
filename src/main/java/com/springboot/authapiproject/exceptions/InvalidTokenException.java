package com.springboot.authapiproject.exceptions;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String message) {
        super("Invalid token");
    }
}



