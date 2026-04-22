package com.taller.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String biography;
    private String email;

    // 🔥 RELACIÓN INVERSA (CLAVE)
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Book> books;
}