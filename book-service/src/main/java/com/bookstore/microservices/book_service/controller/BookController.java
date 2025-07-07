package com.bookstore.microservices.book_service.controller;

import com.bookstore.microservices.book_service.model.BookEntity;
import com.bookstore.microservices.book_service.service.BookService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/books")
public class BookController {
    final
    BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<?> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PostMapping
    public ResponseEntity<?> createBook(BookEntity book) {
        bookService.addBook(book);
        return ResponseEntity.status(201).body("Book created successfully");
    }

    @DeleteMapping
    public ResponseEntity<?> deleteBook(Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok("Book deleted successfully");
    }

    @PutMapping
    public ResponseEntity<?> updateBook(Long id, BookEntity book) {
        bookService.updateBook(id, book);
        return ResponseEntity.ok("Book updated successfully");
    }

    @GetMapping("/available/{id}")
    public ResponseEntity<?> isAvailable(@PathVariable Long id) {
        Boolean available = bookService.isAvailable(id);
        if (available) {
            return ResponseEntity.ok("Book is available");
        } else {
            return ResponseEntity.status(404).body("Book not found or not available");
        }
    }

    @PutMapping("/decrement/{id}")
    public ResponseEntity<?> decrementAvailableCopies(@PathVariable Long id) {
        try {
            bookService.decrementAvailableCopies(id);
            return ResponseEntity.ok("Available copies decremented successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
