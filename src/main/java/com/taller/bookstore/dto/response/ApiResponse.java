package com.taller.bookstore.dto.response;

import java.time.Instant;

public class ApiResponse<T> {

    private String status;
    private int code;
    private String message;
    private T data;
    private Instant timestamp;

    public ApiResponse(T data, String message) {
        this.status = "success";
        this.code = 200;
        this.message = message;
        this.data = data;
        this.timestamp = Instant.now();
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(data, message);
    }

    // 👇 getters OBLIGATORIOS
    public String getStatus() { return status; }
    public int getCode() { return code; }
    public String getMessage() { return message; }
    public T getData() { return data; }
    public Instant getTimestamp() { return timestamp; }
}