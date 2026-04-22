package com.taller.bookstore.service;

import com.taller.bookstore.dto.request.CategoryRequest;
import com.taller.bookstore.dto.response.ApiResponse;
import com.taller.bookstore.dto.response.CategoryResponse;

public interface CategoryService {
    ApiResponse<CategoryResponse> create(CategoryRequest request);
}