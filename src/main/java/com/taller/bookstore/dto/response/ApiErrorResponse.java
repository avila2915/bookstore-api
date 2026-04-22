package com.taller.bookstore.dto.response;

import java.time.Instant;
import java.util.List;

import lombok.Getter;

@Getter
public class ApiErrorResponse {

    private final String status;
    private final int code;
    private final String message;
    private final List<String> errors;
    private final Instant timestamp;
    private final String path;

    public ApiErrorResponse(int code, String message, List<String> errors, String path) {
        this.status = "error";
        this.code = code;
        this.message = message;
        this.errors = errors;
        this.timestamp = Instant.now();
        this.path = path;
    }
}