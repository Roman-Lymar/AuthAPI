package com.springboot.authapiproject.exceptions;

public class EmptyFieldException extends RuntimeException {

    public EmptyFieldException(String message) {
        super(message);
    }
}

