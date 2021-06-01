package com.dergachev.homework2.dogfarm.util.exception;

public class DateException extends Exception {
    public DateException() {
    }

    public DateException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "DogException{message = " + getMessage() + "}";
    }
}

