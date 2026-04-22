package com.taller.bookstore.service;

import com.taller.bookstore.dto.request.BookRequest;
import com.taller.bookstore.dto.response.BookResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

    BookResponse createBook(BookRequest request);

    Page<BookResponse> getAllBooks(Pageable pageable);

    BookResponse getBookById(Long id);

    void deleteBook(Long id);
}