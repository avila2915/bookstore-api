package com.taller.bookstore.dto.response;

import java.time.Instant;

public class ApiResponse<T> {

    private String status;
    private int code;
    private String message;
    private T data;
    private Instant timestamp;

    // ✅ Constructor principal (el que ya usas)
    public ApiResponse(T data, String message) {
        this.status = "success";
        this.code = 200;
        this.message = message;
        this.data = data;
        this.timestamp = Instant.now();
    }

    // ✅ Constructor completo (por si lo necesitas después)
    public ApiResponse(String status, int code, String message, T data, Instant timestamp) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = (timestamp != null) ? timestamp : Instant.now();
    }

    // ✅ Método estático recomendado
    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(data, message);
    }

    // ===== GETTERS =====

    public String getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    // ===== SETTERS (opcional pero recomendado) =====

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}