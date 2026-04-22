package com.taller.bookstore.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.taller.bookstore.dto.request.AuthorRequest;
import com.taller.bookstore.dto.response.ApiResponse;
import com.taller.bookstore.dto.response.AuthorResponse;
import com.taller.bookstore.dto.response.BookResponse;
import com.taller.bookstore.entity.Author;
import com.taller.bookstore.exception.custom.AuthorHasBooksException;
import com.taller.bookstore.exception.custom.ResourceNotFoundException;
import com.taller.bookstore.mapper.AuthorMapper;
import com.taller.bookstore.mapper.BookMapper;
import com.taller.bookstore.repository.AuthorRepository;
import com.taller.bookstore.service.AuthorService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final BookMapper bookMapper;

    // 🔹 CREATE
    @Override
    public ApiResponse<AuthorResponse> create(AuthorRequest request) {

        Author author = authorMapper.toEntity(request);
        Author saved = authorRepository.save(author);
        AuthorResponse response = authorMapper.toResponse(saved);

        return ApiResponse.success(response, "Author created successfully");
    }

    // 🔹 GET ALL
    @Override
    public ApiResponse<List<AuthorResponse>> getAll() {

        List<AuthorResponse> list = authorRepository.findAll()
                .stream()
                .map(authorMapper::toResponse)
                .toList();

        return ApiResponse.success(list, "Authors list");
    }

    // 🔹 GET BY ID
    @Override
    public ApiResponse<AuthorResponse> getById(Long id) {

        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));

        return ApiResponse.success(
                authorMapper.toResponse(author),
                "Author found"
        );
    }

    // 🔹 UPDATE
    @Override
    public ApiResponse<AuthorResponse> update(Long id, AuthorRequest request) {

        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));

        author.setName(request.getName());
        author.setBiography(request.getBiography());

        Author updated = authorRepository.save(author);

        return ApiResponse.success(
                authorMapper.toResponse(updated),
                "Author updated"
        );
    }

    // 🔥 DELETE CON VALIDACIÓN (IMPORTANTE)
    @Override
    public ApiResponse<Void> delete(Long id) {

        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));

        if (author.getBooks() != null && !author.getBooks().isEmpty()) {
            throw new AuthorHasBooksException("Author has books and cannot be deleted");
        }

        authorRepository.delete(author);

        return ApiResponse.success(null, "Author deleted");
    }

    // 🔥 🔥 NUEVO (ESTO ES LO QUE PIDE LA RAMA)
    @Override
    public ApiResponse<List<BookResponse>> getBooksByAuthor(Long id) {

        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));

        List<BookResponse> books = author.getBooks()
                .stream()
                .map(bookMapper::toResponse)
                .toList();

        return ApiResponse.success(books, "Books by author");
    }
}