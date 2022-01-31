package com.rabouk.bookstore.repository;

import com.rabouk.bookstore.domain.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : Raouf Bouklab
 * @created : 2022-01-26, Wednesday
 **/

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByFirstNameAndLastName(String firstName, String lastName);
}
