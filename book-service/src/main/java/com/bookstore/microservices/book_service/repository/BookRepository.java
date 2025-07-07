package com.bookstore.microservices.book_service.repository;

import com.bookstore.microservices.book_service.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository  extends JpaRepository<BookEntity, Long> {
}
