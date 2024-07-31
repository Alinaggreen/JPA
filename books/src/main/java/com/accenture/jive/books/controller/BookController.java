package com.accenture.jive.books.controller;

import com.accenture.jive.books.controller.dto.BookDtoRequestBody;
import com.accenture.jive.books.persistence.entity.Author;
import com.accenture.jive.books.persistence.entity.Book;
import com.accenture.jive.books.controller.dto.BookDto;
import com.accenture.jive.books.controller.mapper.BookMapper;
import com.accenture.jive.books.persistence.repository.AuthorRepository;
import com.accenture.jive.books.persistence.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
    private AuthorRepository authorRepository;

    @Autowired
    private BookMapper bookMapper;

    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> readAllBooks(
            @RequestParam(name = "order", required = false, defaultValue = "ASC") String order) {

        Sort.Direction direction = Sort.Direction.valueOf(order);

        if ("DESC".equalsIgnoreCase(order)) {
            direction = Sort.Direction.DESC;
        }

        List<Book> books = bookRepository.findAll(Sort.by(direction, "title"));
        List<BookDto> booksDto = bookMapper.booksToDtos(books);
        if (booksDto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(booksDto);
    }

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
    public ResponseEntity<BookDto> createOneBook(@RequestBody BookDtoRequestBody bookDtoRequestBody) {
        Book book = bookMapper.dtoToBook(bookDtoRequestBody);
        book.setGuid(UUID.randomUUID().toString());

        Optional<Author> optionalAuthor =
                authorRepository.findByName(bookDtoRequestBody.getAuthorFirstName(), bookDtoRequestBody.getAuthorLastName());
        Author author;

        if (optionalAuthor.isEmpty()) {
            author = new Author();
            author.setFirstName(bookDtoRequestBody.getAuthorFirstName());
            author.setLastName(bookDtoRequestBody.getAuthorLastName());
            authorRepository.save(author);
        } else {
            author = optionalAuthor.get();
        }

        book.setAuthor(author);
        Book createdBook = bookRepository.save(book);
        if (createdBook.getId() == null) {
            return ResponseEntity.internalServerError().build();
        }
        BookDto bookDto = bookMapper.bookToDto(createdBook);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookDto);
    }

    @PutMapping ("/books/{bookGuid}")
    public ResponseEntity<?> updateOneBook(@PathVariable("bookGuid") String guid, @RequestBody BookDtoRequestBody bookDtoRequestBody) {
        Optional<Book> oldBook = bookRepository.findByGuid(guid);

        if (oldBook.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Book book = bookMapper.dtoToBook(bookDtoRequestBody);
        book.setId(oldBook.get().getId());
        book.setGuid(guid);

        Optional<Author> optionalAuthor =
                authorRepository.findByName(bookDtoRequestBody.getAuthorFirstName(), bookDtoRequestBody.getAuthorLastName());
        Author author;

        if (optionalAuthor.isEmpty()) {
            author = new Author();
            author.setFirstName(bookDtoRequestBody.getAuthorFirstName());
            author.setLastName(bookDtoRequestBody.getAuthorLastName());
            authorRepository.save(author);
        } else {
            author = optionalAuthor.get();
        }

        book.setAuthor(author);
        Book updatedBook = bookRepository.save(book);
        BookDto bookDto = bookMapper.bookToDto(updatedBook);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookDto);
    }
}