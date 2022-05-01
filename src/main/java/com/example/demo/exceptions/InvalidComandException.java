package com.example.demo.exceptions;

public class InvalidComandException extends RuntimeException {

    public InvalidComandException(String message) {
        super(message);
    }
}
