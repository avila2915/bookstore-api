package com.taller.bookstore.controller;

import com.taller.bookstore.dto.request.BookRequest;
import com.taller.bookstore.dto.response.ApiResponse;
import com.taller.bookstore.dto.response.BookResponse;
import com.taller.bookstore.service.BookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // 🔓 PÚBLICO - LISTAR TODOS
    @GetMapping
    public ResponseEntity<ApiResponse<Page<BookResponse>>> getAllBooks(Pageable pageable) {

        Page<BookResponse> books = bookService.getAllBooks(pageable);

        return ResponseEntity.ok(
                ApiResponse.success(books, "Books retrieved successfully")
        );
    }

    // 🔓 PÚBLICO - OBTENER POR ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BookResponse>> getBookById(@PathVariable Long id) {

        BookResponse book = bookService.getBookById(id);

        return ResponseEntity.ok(
                ApiResponse.success(book, "Book found")
        );
    }

    // 🔐 SOLO ADMIN - CREAR LIBRO
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<BookResponse>> createBook(
            @Valid @RequestBody BookRequest request) {

        BookResponse created = bookService.createBook(request);

        return ResponseEntity.ok(
                ApiResponse.success(created, "Book created successfully")
        );
    }

    // 🔐 SOLO ADMIN - ELIMINAR LIBRO
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteBook(@PathVariable Long id) {

        bookService.deleteBook(id);

        return ResponseEntity.ok(
                ApiResponse.success(null, "Book deleted successfully")
        );
    }
}