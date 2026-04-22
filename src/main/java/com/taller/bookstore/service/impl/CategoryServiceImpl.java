package com.taller.bookstore.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.taller.bookstore.dto.request.CategoryRequest;
import com.taller.bookstore.dto.response.ApiResponse;
import com.taller.bookstore.dto.response.CategoryResponse;
import com.taller.bookstore.entity.Category;
import com.taller.bookstore.mapper.CategoryMapper;
import com.taller.bookstore.repository.CategoryRepository;
import com.taller.bookstore.service.CategoryService;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public ApiResponse<CategoryResponse> create(CategoryRequest request) {

        Category category = categoryMapper.toEntity(request);

        Category saved = categoryRepository.save(category);

        CategoryResponse response = categoryMapper.toResponse(saved);

        return ApiResponse.success(response, "Category created successfully");
    }
}