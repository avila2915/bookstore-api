package com.taller.bookstore.service;

import com.taller.bookstore.dto.request.AuthorRequest;
import com.taller.bookstore.dto.response.ApiResponse;
import com.taller.bookstore.dto.response.AuthorResponse;
import com.taller.bookstore.dto.response.BookResponse;

import java.util.List;

public interface AuthorService {

    ApiResponse<AuthorResponse> create(AuthorRequest request);

    // 🔹 AGREGA ESTOS ↓↓↓

    ApiResponse<List<AuthorResponse>> getAll();

    ApiResponse<AuthorResponse> getById(Long id);

    ApiResponse<AuthorResponse> update(Long id, AuthorRequest request);

    ApiResponse<Void> delete(Long id);

    // 🔥 IMPORTANTE (la nueva funcionalidad)
    ApiResponse<List<BookResponse>> getBooksByAuthor(Long id);
}