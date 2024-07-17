package com.accenture.jive.books.persistence.repository;

import com.accenture.jive.books.persistence.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}