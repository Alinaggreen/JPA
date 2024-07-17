package com.accenture.jive.books;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(source = "book.author.firstName", target = "authorFirstName")
    @Mapping(source = "book.author.lastName", target = "authorLastName")
    BookDto bookToDto(Book book);

    List<BookDto> booksToDtos(List<Book> books);
}
