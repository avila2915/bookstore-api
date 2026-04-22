package com.taller.bookstore.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.taller.bookstore.dto.request.AuthorRequest;
import com.taller.bookstore.dto.response.ApiResponse;
import com.taller.bookstore.dto.response.AuthorResponse;
import com.taller.bookstore.entity.Author;
import com.taller.bookstore.mapper.AuthorMapper;
import com.taller.bookstore.repository.AuthorRepository;
import com.taller.bookstore.service.AuthorService;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public ApiResponse<AuthorResponse> create(AuthorRequest request) {

        Author author = authorMapper.toEntity(request);

        Author saved = authorRepository.save(author);

        AuthorResponse response = authorMapper.toResponse(saved);

        return ApiResponse.success(response, "Author created successfully");
    }
}