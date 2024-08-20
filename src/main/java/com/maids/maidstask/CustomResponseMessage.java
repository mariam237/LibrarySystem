package com.maids.maidstask;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class CustomResponseMessage {
    private HttpStatus status;
    private String message;
    private String timestamp;

    public CustomResponseMessage(HttpStatus status, String message) {
        this.status =  status;
        this.message = message;
        this.timestamp = LocalDateTime.now().toString();
    }
}
