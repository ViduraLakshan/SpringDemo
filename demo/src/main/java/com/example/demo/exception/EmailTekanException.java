package com.example.demo.exception;

public class EmailTekanException extends RuntimeException{
    public EmailTekanException() {
        super();
    }

    public EmailTekanException(String message) {
        super(message);
    }

    public EmailTekanException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailTekanException(Throwable cause) {
        super(cause);
    }

    protected EmailTekanException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
