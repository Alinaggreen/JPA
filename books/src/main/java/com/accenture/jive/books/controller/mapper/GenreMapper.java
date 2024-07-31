package com.accenture.jive.books.controller.mapper;

import com.accenture.jive.books.controller.dto.GenreDto;
import com.accenture.jive.books.persistence.entity.Genre;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDto genreToDto(Genre genre);
}
