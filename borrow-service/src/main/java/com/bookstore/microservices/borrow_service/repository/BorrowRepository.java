package com.bookstore.microservices.borrow_service.repository;

import com.bookstore.microservices.borrow_service.model.BorrowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRepository extends JpaRepository<BorrowEntity, Long> {
}
