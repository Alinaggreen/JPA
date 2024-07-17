package com.accenture.jive.books.controller.mapper;

import com.accenture.jive.books.controller.dto.AuthorDto;
import com.accenture.jive.books.persistence.entity.Author;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = BookMapper.class)
public interface AuthorMapper {

    AuthorDto authorToDto(Author author);

    List<AuthorDto> authorsToDtos(List<Author> authors);

}