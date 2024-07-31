package com.accenture.jive.books.controller.mapper;

import com.accenture.jive.books.controller.dto.BookDto;
import com.accenture.jive.books.controller.dto.BookDtoRequestBody;
import com.accenture.jive.books.controller.dto.GenreDto;
import com.accenture.jive.books.persistence.entity.Author;
import com.accenture.jive.books.persistence.entity.Book;
import com.accenture.jive.books.persistence.entity.Genre;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-31T19:07:55+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Autowired
    private GenreMapper genreMapper;

    @Override
    public BookDto bookToDto(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDto bookDto = new BookDto();

        bookDto.setAuthorFirstName( bookAuthorFirstName( book ) );
        bookDto.setAuthorLastName( bookAuthorLastName( book ) );
        bookDto.setGenres( genreSetToGenreDtoSet( book.getGenres() ) );
        bookDto.setGuid( book.getGuid() );
        bookDto.setTitle( book.getTitle() );

        return bookDto;
    }

    @Override
    public List<BookDto> booksToDtos(List<Book> books) {
        if ( books == null ) {
            return null;
        }

        List<BookDto> list = new ArrayList<BookDto>( books.size() );
        for ( Book book : books ) {
            list.add( bookToDto( book ) );
        }

        return list;
    }

    @Override
    public Book dtoToBook(BookDtoRequestBody bookDtoRequestBody) {
        if ( bookDtoRequestBody == null ) {
            return null;
        }

        Book book = new Book();

        book.setTitle( bookDtoRequestBody.getTitle() );

        return book;
    }

    private String bookAuthorFirstName(Book book) {
        if ( book == null ) {
            return null;
        }
        Author author = book.getAuthor();
        if ( author == null ) {
            return null;
        }
        String firstName = author.getFirstName();
        if ( firstName == null ) {
            return null;
        }
        return firstName;
    }

    private String bookAuthorLastName(Book book) {
        if ( book == null ) {
            return null;
        }
        Author author = book.getAuthor();
        if ( author == null ) {
            return null;
        }
        String lastName = author.getLastName();
        if ( lastName == null ) {
            return null;
        }
        return lastName;
    }

    protected Set<GenreDto> genreSetToGenreDtoSet(Set<Genre> set) {
        if ( set == null ) {
            return null;
        }

        Set<GenreDto> set1 = new LinkedHashSet<GenreDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Genre genre : set ) {
            set1.add( genreMapper.genreToDto( genre ) );
        }

        return set1;
    }
}
