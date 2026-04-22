package com.taller.bookstore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.taller.bookstore.dto.request.AuthorRequest;
import com.taller.bookstore.dto.response.ApiResponse;
import com.taller.bookstore.dto.response.AuthorResponse;
import com.taller.bookstore.service.AuthorService;
import java.util.List;
import com.taller.bookstore.dto.response.BookResponse;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/{id}/books")
    public ApiResponse<List<BookResponse>> getBooksByAuthor(@PathVariable Long id) {
        return authorService.getBooksByAuthor(id);
    }
    @PostMapping
    public ApiResponse<AuthorResponse> create(@RequestBody AuthorRequest request) {
        return authorService.create(request);
    }
}