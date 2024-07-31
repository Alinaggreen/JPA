package com.accenture.jive.books.controller.mapper;

import com.accenture.jive.books.controller.dto.AuthorDto;
import com.accenture.jive.books.controller.dto.BookDto;
import com.accenture.jive.books.persistence.entity.Author;
import com.accenture.jive.books.persistence.entity.Book;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-31T17:09:42+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class AuthorMapperImpl implements AuthorMapper {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public AuthorDto authorToDto(Author author) {
        if ( author == null ) {
            return null;
        }

        AuthorDto authorDto = new AuthorDto();

        authorDto.setFirstName( author.getFirstName() );
        authorDto.setLastName( author.getLastName() );
        authorDto.setBooks( bookSetToBookDtoSet( author.getBooks() ) );

        return authorDto;
    }

    @Override
    public List<AuthorDto> authorsToDtos(List<Author> authors) {
        if ( authors == null ) {
            return null;
        }

        List<AuthorDto> list = new ArrayList<AuthorDto>( authors.size() );
        for ( Author author : authors ) {
            list.add( authorToDto( author ) );
        }

        return list;
    }

    protected Set<BookDto> bookSetToBookDtoSet(Set<Book> set) {
        if ( set == null ) {
            return null;
        }

        Set<BookDto> set1 = new LinkedHashSet<BookDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Book book : set ) {
            set1.add( bookMapper.bookToDto( book ) );
        }

        return set1;
    }
}
