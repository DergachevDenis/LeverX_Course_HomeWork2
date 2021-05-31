package com.dergachev.homework2.dogfarm.exception;

public class DogException extends Exception{
    public DogException() {
    }

    public DogException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "DogException{message = " + getMessage() + "}";
    }
}
