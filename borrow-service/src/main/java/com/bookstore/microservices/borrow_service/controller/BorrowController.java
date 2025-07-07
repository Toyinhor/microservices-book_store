package com.bookstore.microservices.borrow_service.controller;

import com.bookstore.microservices.borrow_service.model.BorrowEntity;
import com.bookstore.microservices.borrow_service.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrows")
public class BorrowController {
    final
    BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @PostMapping
    public ResponseEntity<?> borrowBook(Long bookId, Long userId) {
        try {
            borrowService.borrowBook(userId, bookId);
            return ResponseEntity.ok("Book borrowed successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllBorrows() {
        return ResponseEntity.ok(borrowService.getAllBorrows());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BorrowEntity>> getBorrowedBooksByUserId(@PathVariable Long userId) {
        try {
            List<BorrowEntity> borrowedBooks = borrowService.getBorrowedBooksByUserId(userId);
            return ResponseEntity.ok(borrowedBooks);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(null);
        }
    }
}
