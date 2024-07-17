package com.accenture.jive.books.persistence.repository;

import com.accenture.jive.books.persistence.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM author WHERE first_name=? AND last_name=?")
    Optional<Author> findByName(String firstName, String lastName);

}