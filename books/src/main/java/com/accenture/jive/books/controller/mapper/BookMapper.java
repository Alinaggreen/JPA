package com.accenture.jive.books.controller.mapper;

import com.accenture.jive.books.controller.dto.BookDtoRequestBody;
import com.accenture.jive.books.persistence.entity.Book;
import com.accenture.jive.books.controller.dto.BookDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(source = "book.author.firstName", target = "authorFirstName")
    @Mapping(source = "book.author.lastName", target = "authorLastName")
    BookDto bookToDto(Book book);

    List<BookDto> booksToDtos(List<Book> books);

    Book dtoToBook(BookDtoRequestBody bookDtoRequestBody);

}
