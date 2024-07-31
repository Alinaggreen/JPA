package com.accenture.jive.books.persistence.entity;

import jakarta.persistence.*;
import org.mapstruct.Mapping;

import java.util.Set;

@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "genres")
    private Set<Book> books;

}
