package com.rabouk.bookstore.repository;

import com.rabouk.bookstore.domain.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Raouf Bouklab
 * @created : 2022-01-26, Wednesday
 **/

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
