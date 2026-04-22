package com.taller.bookstore.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {

    private Long id;
    private String title;
    private String isbn;
    private Double price;
    private Integer stock;

    // 🔹 NO DEVOLVEMOS ENTIDAD COMPLETA
    private AuthorResponse author;

    // 🔹 EVITA RECURSIÓN
    private List<CategoryResponse> categories;
}