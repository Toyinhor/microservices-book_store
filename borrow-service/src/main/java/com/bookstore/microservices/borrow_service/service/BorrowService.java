package com.bookstore.microservices.borrow_service.service;

import com.bookstore.microservices.borrow_service.client.BookClient;
import com.bookstore.microservices.borrow_service.model.BorrowEntity;
import com.bookstore.microservices.borrow_service.repository.BorrowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowService {

    private final BookClient bookClient;
    private final BorrowRepository borrowRepository;
    private final KafkaProducerService kafkaProducerService;

    public List<BorrowEntity> getAllBorrows() {
        return borrowRepository.findAll();
    }

    public void borrowBook(Long userId, Long bookId) {
        if (!bookClient.isAvailable(bookId).equals("Book is available")){
            throw new RuntimeException("Book is not available for borrowing");
        }

        bookClient.decrementAvailableCopies(bookId);

        BorrowEntity borrow = new BorrowEntity();
        borrow.setUserId(userId);
        borrow.setBookId(bookId);
        borrow.setBorrowDate(LocalDate.from(LocalDateTime.now()));
        borrow.setDueDate(null);
        borrow.setReturnDate(null);
        borrow.setStatus("BORROWED");
        borrowRepository.save(borrow);

        kafkaProducerService.sendMessage("User borrowed bookId: " + bookId);

    }

    public List<BorrowEntity> getBorrowedBooksByUserId(Long userId) {
        List<BorrowEntity> borrows = borrowRepository.findAll();
        List<BorrowEntity> returnList = new ArrayList<>();
        for (BorrowEntity borrow : borrows) {
            if (borrow.getUserId().equals(userId)) {
                returnList.add(borrow);
            }
        }
        if (returnList.isEmpty()) {
            throw new RuntimeException("No borrowed books found for user with id: " + userId);
        }
        return returnList;
    }
}
