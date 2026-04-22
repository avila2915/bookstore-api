package com.taller.bookstore.exception.handler;

import com.taller.bookstore.dto.response.ApiErrorResponse;
import com.taller.bookstore.exception.custom.*;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateResourceException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiErrorResponse handleDuplicate(DuplicateResourceException ex, HttpServletRequest request) {
        return new ApiErrorResponse(
                409,
                ex.getMessage(),
                List.of(ex.getMessage()),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiErrorResponse handleInvalidCredentials(InvalidCredentialsException ex, HttpServletRequest request) {
        return new ApiErrorResponse(
                401,
                ex.getMessage(),
                List.of(ex.getMessage()),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse handleGeneral(Exception ex, HttpServletRequest request) {
        return new ApiErrorResponse(
                500,
                "Internal server error",
                List.of(ex.getMessage()),
                request.getRequestURI()
        );
    }
}