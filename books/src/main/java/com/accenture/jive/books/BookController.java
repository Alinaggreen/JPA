package com.accenture.jive.books;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> readAllBooks() {
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(books);
    }

//    @GetMapping("/books/condition")
//    public List<Book> readAllBooksCondition() {
//        return bookRepository.findByIdCondition();
//    }

    @GetMapping("/books/{bookGuid}")
    public ResponseEntity<Optional<Book>> readOneBook(@PathVariable("bookGuid") String guid) {
        Optional<Book> book = bookRepository.findByGuid(guid);
        if (book.isPresent()) {
            return  ResponseEntity.ok(book);
        }
        return ResponseEntity.notFound().build();
    }

    //TODO: Error Case
    @DeleteMapping ("/books")
    public ResponseEntity<?> removeBooks() {
        bookRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

//    @DeleteMapping ("/books/{bookId}")
//    public ResponseEntity<?> removeOneBook(@PathVariable("bookId") Long id) {
//        bookRepository.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }

    @Transactional
    @DeleteMapping ("/books/{bookGuid}")
    public ResponseEntity<?> removeOneBook(@PathVariable("bookGuid") String guid) {
        if (!bookRepository.existsByGuid(guid)) {
            return ResponseEntity.notFound().build();
        }
        bookRepository.deleteByGuid(guid);
        return ResponseEntity.noContent().build();
    }

    //TODO: Error Case
    @PostMapping ("/books")
    public ResponseEntity<Book> createOneBook(@RequestBody Book book) {
        book.setGuid(UUID.randomUUID().toString());
        bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @PutMapping ("/books/{bookGuid}")
    public ResponseEntity<?> updateOneBook(@PathVariable("bookGuid") String guid, @RequestBody Book book) {
        Optional<Book> oldBook = bookRepository.findByGuid(guid);
        if (oldBook.isPresent()) {
            book.setId(oldBook.get().getId());
            book.setGuid(guid);
            bookRepository.save(book);
            return ResponseEntity.noContent().build();
        }
        bookRepository.save(book);
        return ResponseEntity.noContent().build();
    }

}