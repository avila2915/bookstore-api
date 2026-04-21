package com.taller.bookstore.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class ApiResponse<T> {

    private String status;
    private int code;
    private String message;
    private T data;
    private Instant timestamp;
}