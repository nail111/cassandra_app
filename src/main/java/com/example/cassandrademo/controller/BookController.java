package com.example.cassandrademo.controller;

import com.example.cassandrademo.model.Book;
import com.example.cassandrademo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookRepository repository;

    @GetMapping
    public List<Book> all() {
        return repository.findAll();
    }

    @PostMapping
    public Book create(@RequestBody Book book) {
        if (book.getId() == null) {
            book.setId(UUID.randomUUID());
        }
        return repository.save(book);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> byId(@PathVariable UUID id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}