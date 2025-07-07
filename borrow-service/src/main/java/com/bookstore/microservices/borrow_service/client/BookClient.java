package com.bookstore.microservices.borrow_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "book-service", url = "http://localhost:8081/api/books")
public interface BookClient {
    @GetMapping("/available/{id}")
    String isAvailable(@PathVariable("id") Long id);

    @PutMapping("/decrement/{id}")
    void decrementAvailableCopies(@PathVariable("id") Long id);
}
