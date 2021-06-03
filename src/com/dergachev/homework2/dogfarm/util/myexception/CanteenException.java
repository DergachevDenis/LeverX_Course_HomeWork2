package com.dergachev.homework2.dogfarm.util.myexception;

public class CanteenException extends Exception{
    public CanteenException() {
    }

    public CanteenException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "CanteenException{message = " + getMessage() + "}";
    }
}
