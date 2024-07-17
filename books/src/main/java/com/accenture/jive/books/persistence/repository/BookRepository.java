package com.accenture.jive.books.persistence.repository;

import com.accenture.jive.books.persistence.entity.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByGuid(String guid);

    @Transactional
    void deleteByGuid(String guid);

    boolean existsByGuid(String guid);

    @Query(nativeQuery = true, value = "SELECT * FROM book WHERE id < 55")
    List<Book> findByIdCondition();
}