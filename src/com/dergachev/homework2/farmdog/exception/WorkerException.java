package com.dergachev.homework2.farmdog.exception;

public class WorkerException extends Exception{

    public WorkerException() {
    }

    public WorkerException(String message) {
        super(message);
    }

    @Override
    public String toString() {
         return "WorkerException{message = " + getMessage() + "}";
    }
}
