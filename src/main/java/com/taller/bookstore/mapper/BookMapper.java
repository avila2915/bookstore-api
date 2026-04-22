package com.taller.bookstore.mapper;

import com.taller.bookstore.entity.Book;
import com.taller.bookstore.dto.response.BookResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookMapper {

    private final AuthorMapper authorMapper;
    private final CategoryMapper categoryMapper;

    public BookResponse toResponse(Book book) {
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getIsbn(),
                book.getPrice(),
                book.getStock(),
                authorMapper.toResponse(book.getAuthor()),
                book.getCategories().stream()
                        .map(categoryMapper::toResponse)
                        .toList()
        );
    }
}