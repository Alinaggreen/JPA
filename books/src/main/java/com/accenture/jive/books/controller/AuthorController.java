package com.accenture.jive.books.controller;

import com.accenture.jive.books.controller.dto.AuthorDto;
import com.accenture.jive.books.controller.mapper.AuthorMapper;
import com.accenture.jive.books.persistence.entity.Author;
import com.accenture.jive.books.persistence.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper authorMapper;

    @GetMapping("/authors")
    public ResponseEntity<?> readAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        List<AuthorDto> authorDtos = authorMapper.authorsToDtos(authors);
        if (authorDtos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(authorDtos);
    }

}