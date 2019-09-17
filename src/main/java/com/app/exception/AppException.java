package com.app.exception;

import java.time.LocalDateTime;

public class AppException extends RuntimeException {

    private final String message;
    private final LocalDateTime dateTime;

    public AppException(String message) {
        this.message = message;
        this.dateTime = LocalDateTime.now();
    }

    @Override
    public String getMessage() {
        return "[" + dateTime + "]: " + message;
    }
}
