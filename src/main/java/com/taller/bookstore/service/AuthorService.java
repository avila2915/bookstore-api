package com.taller.bookstore.service;

import com.taller.bookstore.dto.request.AuthorRequest;
import com.taller.bookstore.dto.response.ApiResponse;
import com.taller.bookstore.dto.response.AuthorResponse;

public interface AuthorService {

    ApiResponse<AuthorResponse> create(AuthorRequest request);
}