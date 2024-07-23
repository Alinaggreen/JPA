package com.accenture.jive.books;

import com.accenture.jive.books.controller.BookController;
import com.accenture.jive.books.controller.dto.BookDto;
import com.accenture.jive.books.controller.dto.BookDtoRequestBody;
import com.accenture.jive.books.persistence.entity.Book;
import com.accenture.jive.books.persistence.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class BookControllerTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookController bookController;

    @BeforeEach
    public void cleanBooks() {
        bookRepository.deleteAll();
    }

    @Test
    public void readAllBooks() {
        ResponseEntity<List<BookDto>> response = bookController.readAllBooks();
        List<BookDto> responseBody = response.getBody();

        if (responseBody != null) {
            Assertions.assertEquals(0, responseBody.size());
        }

        BookDtoRequestBody bookDto = createBookDtoRequestBody();

        ResponseEntity<BookDto> createdBook = bookController.createOneBook(bookDto);
        BookDto createdBookBody = createdBook.getBody();
        Assertions.assertNotNull(createdBookBody);

        response = bookController.readAllBooks();
        responseBody = response.getBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals(1, responseBody.size());
    }

    @Test
    public void readOneBook() {
        BookDtoRequestBody bookDto = createBookDtoRequestBody();
        ResponseEntity<BookDto> createdBook = bookController.createOneBook(bookDto);
        BookDto createdBookBody = createdBook.getBody();
        Assertions.assertNotNull(createdBookBody);

        Optional<Book> book = bookRepository.findByTitle(bookDto.getTitle());
        Assertions.assertNotNull(book.get());

        ResponseEntity<BookDto> response = bookController.readOneBook(book.get().getGuid());
        BookDto responseBody = response.getBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals("Hallo", responseBody.getTitle());
        Assertions.assertEquals("Alina", responseBody.getAuthorFirstName());
        Assertions.assertEquals("Grünaug", responseBody.getAuthorLastName());

    }


    private BookDtoRequestBody createBookDtoRequestBody() {
        BookDtoRequestBody book = new BookDtoRequestBody();
        book.setTitle("Hallo");
        book.setAuthorFirstName("Alina");
        book.setAuthorLastName("Grünaug");
        return book;
    }

}
