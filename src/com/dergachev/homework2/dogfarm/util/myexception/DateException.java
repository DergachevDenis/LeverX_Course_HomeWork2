package com.dergachev.homework2.dogfarm.util.myexception;

public class DateException extends Exception {
    public DateException() {
    }

    public DateException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "DateException{message = " + getMessage() + "}";
    }
}

