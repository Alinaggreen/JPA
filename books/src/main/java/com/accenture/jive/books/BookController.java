package com.accenture.jive.books;

import org.springframework.beans.factory.annotation.Autowired;
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

    //TODO: DELETE ALL
    @DeleteMapping ("/books")
    public ResponseEntity<?> removeBooks() {
        bookRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

    //TODO: DELETE ONE
    @DeleteMapping ("/books/{bookGuid}")
    public ResponseEntity<?> removeOneBook(@PathVariable("bookGuid") String guid) {
        bookRepository.deleteByGuid(guid);
        return ResponseEntity.noContent().build();
    }

    //TODO: POST
    //Fehlermeldung von uns mit ResponseEntity oder macht das Spring selber?
    @PostMapping ("/books")
    public ResponseEntity<Book> createOneBook(@RequestBody Book book) {
        book.setGuid(UUID.randomUUID().toString());
        bookRepository.save(book);
        return ResponseEntity.internalServerError().build();
    }

    //TODO: PUT
    public ResponseEntity<?> updateOneBook(@PathVariable("bookGuid") String guid, @RequestBody Book book) {
        book.setGuid(guid);
        bookRepository.save(book);
        return ResponseEntity.noContent().build();
    }

}