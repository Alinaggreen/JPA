package com.accenture.jive.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    @Autowired
    BookRepository bookRepository;

    @GetMapping("/books")
    public List<Book> readAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/books/condition")
    public List<Book> readAllBooksCondition() {
        return bookRepository.findByIdCondition();
    }

//    @GetMapping("/books/{bookId}")
//    public Optional<Book> readOneBook(@PathVariable("bookId") Long id) {
//        return bookRepository.findById(id);
//    }

    @GetMapping("/books/{bookGuid}")
    public Optional<Book> readOneBook(@PathVariable("bookGuid") String guid) {
        return bookRepository.findByGuid(guid);
    }
}
