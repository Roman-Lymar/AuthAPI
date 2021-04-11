package com.springboot.authapiproject.exceptions;

public class InvalidToken extends RuntimeException {
    public InvalidToken(String message) {
        super(message);
    }
}



