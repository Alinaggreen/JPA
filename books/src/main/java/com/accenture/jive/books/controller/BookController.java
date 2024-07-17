package com.accenture.jive.books.controller;

import com.accenture.jive.books.persistence.entity.Book;
import com.accenture.jive.books.controller.dto.BookDto;
import com.accenture.jive.books.controller.mapper.BookMapper;
import com.accenture.jive.books.persistence.repository.BookRepository;
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
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> readAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookDto> booksDto = bookMapper.booksToDtos(books);
        if (booksDto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(booksDto);
    }

//    @GetMapping("/books/condition")
//    public List<Book> readAllBooksCondition() {
//        return bookRepository.findByIdCondition();
//    }

    @GetMapping("/books/{bookGuid}")
    public ResponseEntity<BookDto> readOneBook(@PathVariable("bookGuid") String guid) {
        Optional<Book> book = bookRepository.findByGuid(guid);
        if (book.isPresent()) {
            BookDto bookDto = bookMapper.bookToDto(book.get());
            return ResponseEntity.ok(bookDto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping ("/books")
    public ResponseEntity<?> removeBooks() {
        try {
            bookRepository.deleteAll();
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
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

    @PostMapping ("/books")
    public ResponseEntity<Book> createOneBook(@RequestBody Book book) {
        book.setGuid(UUID.randomUUID().toString());
        Book createdBook = bookRepository.save(book);
        if (createdBook.getId() == null) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
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
        return ResponseEntity.notFound().build();
    }

}