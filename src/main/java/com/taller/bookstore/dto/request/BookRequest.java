package com.taller.bookstore.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {

    @NotBlank
    @Size(max = 200)
    private String title;

    @NotBlank
    private String isbn;

    @NotNull
    @Positive
    private Double price;

    @NotNull
    @Min(0)
    private Integer stock;

    @NotNull
    private Long authorId;

    private List<Long> categoryIds;
}