package com.bookstore.microservices.book_service.service;

import com.bookstore.microservices.book_service.model.BookEntity;
import com.bookstore.microservices.book_service.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }

    public void addBook(BookEntity book) {
        bookRepository.save(book);
    }

    public void updateBook(Long id, BookEntity book) {
        if (bookRepository.existsById(id)) {
            book.setId(id);
            bookRepository.save(book);
        } else {
            throw new RuntimeException("Book not found with id: " + id);
        }
    }

    public void deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        } else {
            throw new RuntimeException("Book not found with id: " + id);
        }
    }

    public Boolean isAvailable(Long id){
        BookEntity book = bookRepository.findById(id).get();
        if (book.getAvailableCopies() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void decrementAvailableCopies(Long id) {
        BookEntity book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        if (book.getAvailableCopies() > 0) {
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            bookRepository.save(book);
        } else {
            throw new RuntimeException("No available copies for book with id: " + id);
        }
    }
}
