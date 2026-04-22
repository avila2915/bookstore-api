package com.taller.bookstore.dto.response;

import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {

    private Long id;
    private String title;
    private String isbn;

    private BigDecimal price; // ✅ CORREGIDO

    private Integer stock;

    private AuthorResponse author;
    private List<CategoryResponse> categories;
}