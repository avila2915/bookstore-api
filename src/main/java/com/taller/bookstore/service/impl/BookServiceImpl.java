package com.taller.bookstore.service.impl;

import com.taller.bookstore.dto.request.BookRequest;
import com.taller.bookstore.dto.response.BookResponse;
import com.taller.bookstore.entity.Author;
import com.taller.bookstore.entity.Book;
import com.taller.bookstore.entity.Category;
import com.taller.bookstore.exception.custom.ResourceNotFoundException;
import com.taller.bookstore.mapper.BookMapper;
import com.taller.bookstore.repository.AuthorRepository;
import com.taller.bookstore.repository.BookRepository;
import com.taller.bookstore.repository.CategoryRepository;
import com.taller.bookstore.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final BookMapper bookMapper;

    // 🔥 CREAR LIBRO (LO MÁS IMPORTANTE)
    @Override
    public BookResponse createBook(BookRequest request) {

        // 🔹 1. Buscar Author
        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));

        // 🔹 2. Buscar categorías
        List<Category> categories = request.getCategoryIds() != null
                ? categoryRepository.findAllById(request.getCategoryIds())
                : List.of();

        // 🔹 3. Crear Book
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setIsbn(request.getIsbn());
        book.setPrice(request.getPrice());
        book.setStock(request.getStock());
        book.setAuthor(author);
        book.setCategories(categories);

        // 🔹 4. Guardar
        Book saved = bookRepository.save(book);

        // 🔹 5. Convertir a response
        return bookMapper.toResponse(saved);
    }

    // 🔹 LISTAR TODOS
    @Override
    public Page<BookResponse> getAllBooks(Pageable pageable) {

        Page<Book> books = bookRepository.findAll(pageable);

        return books.map(bookMapper::toResponse);
    }

    // 🔹 BUSCAR POR ID
    @Override
    public BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        return bookMapper.toResponse(book);
    }

    // 🔹 ELIMINAR
    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        bookRepository.delete(book);
    }
}