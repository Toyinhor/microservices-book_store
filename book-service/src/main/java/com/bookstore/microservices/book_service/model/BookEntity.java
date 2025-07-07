package com.bookstore.microservices.book_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    private String category;

    private Integer totalCopies;

    private Integer availableCopies;

    private String publisher;

    private Integer publishYear;

    private String language;

    @Column(length = 1000)
    private String description;
}
