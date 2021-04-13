package com.springboot.authapiproject.exceptions;

public class AccessDeniedException extends RuntimeException{

    public AccessDeniedException() {
        super("Access denied");
    }
}
